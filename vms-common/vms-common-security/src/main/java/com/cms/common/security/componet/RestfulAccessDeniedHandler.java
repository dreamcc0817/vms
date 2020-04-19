package com.cms.common.security.componet;

import cn.hutool.json.JSONUtil;
import com.bonc.common.core.util.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Title: vms
 * @Package: com.cms.common.security.componet
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/4/19 17:51
 * @Version: V1.0
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control","no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(JSONUtil.parse(R.forbidden(e.getMessage())));
		response.getWriter().flush();
	}
}
