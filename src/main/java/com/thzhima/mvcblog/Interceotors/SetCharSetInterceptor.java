package com.thzhima.mvcblog.Interceotors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//�����ַ���������---------�̳���---------------  ������������������� 
public class SetCharSetInterceptor extends HandlerInterceptorAdapter{

//��Handle����֮ǰִ��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		System.out.println("---------utf-8-------------");
		return true;//����return����ֵ��������л��߲�����
	}

//��Handle����֮��ִ��
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
	}

//�ڵ�ǰ�����������ҳ��ʱִ��
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

//��������������
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
	}
}
