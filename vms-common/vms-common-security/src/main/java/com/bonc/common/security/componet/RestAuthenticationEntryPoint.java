package com.bonc.common.security.componet;

import cn.hutool.json.JSONUtil;
import com.bonc.common.core.util.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: vms
 * @Package: com.cms.common.security.componet
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/4/19 18:16
 * @Version: V1.0
 */
//@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control","no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(JSONUtil.parse(R.unauthorized(e.getMessage())));
		response.getWriter().flush();
	}
}
