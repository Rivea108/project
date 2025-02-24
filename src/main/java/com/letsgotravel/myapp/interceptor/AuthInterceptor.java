package com.letsgotravel.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				
		HttpSession session = request.getSession();
		
		if(session.getAttribute("midx") == null) {
			
			saveUrl(request);
			
			response.sendRedirect(request.getContextPath() + "/member/memberLogin.do");
			
			return false;
			
		} else {
			
			return true;
		}
		
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}
	
	public void saveUrl(HttpServletRequest request) {
		
	    String fullUrl = request.getRequestURL().toString();
	    
	    String contextPath = request.getContextPath();
	    
	    String cleanUrl = fullUrl.replaceFirst(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath, "");
	    	    
		String param = request.getQueryString();  //
		
		if(param == null || param.equals("null") || param.equals("")) {
			param = "";
		} else {
			param = "?" + param;
		}
		
		String locationUrl = cleanUrl + param;
		
		HttpSession session = request.getSession();
		if(request.getMethod().equals("GET")) {
			session.setAttribute("saveUrl", locationUrl);
		}
	}
}
