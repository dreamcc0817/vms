package com.dreamcc.gateway.service.impl;

import com.dreamcc.common.core.constant.Constants;
import com.dreamcc.common.core.exception.CaptchaException;
import com.dreamcc.common.core.utils.IdUtils;
import com.dreamcc.common.core.utils.StringUtils;
import com.dreamcc.common.core.utils.sign.Base64;
import com.dreamcc.common.core.web.domain.AjaxResult;
import com.dreamcc.gateway.config.properties.CaptchaProperties;
import com.dreamcc.gateway.service.ValidateCodeService;
import com.dreamcc.redis.service.RedisService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码实现处理
 *
 * @author ruoyi
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private RedisService redisService;

    @Autowired
    private CaptchaProperties captchaProperties;

    /**
     * 生成验证码
     */
    @Override
    public AjaxResult createCapcha() throws IOException, CaptchaException {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaOnOff = captchaProperties.getEnabled();
        ajax.put("captchaOnOff", captchaOnOff);
        if (!captchaOnOff) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        String captchaType = captchaProperties.getType();
        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        //  redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCapcha(String code, String uuid) throws CaptchaException {
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            throw new CaptchaException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException("验证码错误");
        }
    }
}
