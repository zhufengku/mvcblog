package com.thzhima.mvcblog.Interceotors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//设置字符集拦截器---------继承了---------------  处理程序拦截器适配器 
public class SetCharSetInterceptor extends HandlerInterceptorAdapter{

//在Handle调用之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		System.out.println("---------utf-8-------------");
		return true;//必须return布尔值，代表放行或者不放行
	}

//在Handle调用之后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
	}

//在当前请求结束返回页面时执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

//并发处理启动后
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
	}
}
