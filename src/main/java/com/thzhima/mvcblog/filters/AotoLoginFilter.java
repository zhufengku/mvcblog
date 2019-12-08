package com.thzhima.mvcblog.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.sun.javafx.webkit.WebConsoleListener;
import com.thzhima.mvcblog.beans.User;
import com.thzhima.mvcblog.services.UserService;

//@WebFilter("/*")有类上注解了就不需在web.xml里配置了就不需要在注解申明
//通过过滤器实现自动登录
@Component(value="autoLoginFilter")
public class AotoLoginFilter implements Filter{

	@Autowired
	private UserService userService;
//	public UserService getUserService() {
//		return ContextLoader.getCurrentWebApplicationContext().getBean(UserService.class);
//	}
		
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
		 HttpSession session = request.getSession(true);
		 Object o = session.getAttribute("userInfo");
		 //先判断session里的key是否为空
		 if(null == o) {
			 Cookie[] cks = request.getCookies();
			 //将键值对的Cookie值放入Map中
			 Map<String, String> map = new HashMap<>();			 
			 if(null != cks)
			 //遍历Cookie里的值，然后put到泛型map里	 
			 for(Cookie c: cks) {
				 String name = c.getName();
				 String value = c.getValue();
				 map.put(name, value);
			 }
			 String userName = map.get("userName");
			 String pwd = map.get("pwd");
			 if(userName!=null && pwd!=null) {
				 User u = new User(null, userName, pwd);
				 u = this.userService.login(u);
				 session.setAttribute("userInfo", u);
			 }
			 
			 
		 }
		 
		 chain.doFilter(req, res);
		 
	}

}
