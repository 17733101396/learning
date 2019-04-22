package com.hainu.hrms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

@Component
public class TokenInterceptor implements HandlerInterceptor {
	private static final Logger log = Logger.getLogger(TokenInterceptor.class);
	@Autowired
	TokenUtil tokenUtil;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2)  {
		try {
			arg1.setHeader("Content-type", "application/json;charset=utf-8");
			String token=arg0.getHeader("access-token");
			if (!StringUtils.isNullOrEmpty(token)) {
				if (tokenUtil.verifyToken(token)) {
					return true;
				}
				log.debug("token过期");
				arg1.setStatus(401);
				arg1.getWriter().write(401);
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
