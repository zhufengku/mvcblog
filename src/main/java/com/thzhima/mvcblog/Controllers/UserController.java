package com.thzhima.mvcblog.Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thzhima.mvcblog.beans.Blog;
import com.thzhima.mvcblog.beans.User;
import com.thzhima.mvcblog.services.BlogService;
import com.thzhima.mvcblog.services.UserService;

import sun.rmi.log.LogOutputStream;

//控制层方法

/**
 * @author zhufeng
 *
 */
/**
 * @author zhufeng
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService UserService;
	
	
	@Autowired
	private BlogService blogService;

	
	//注册
	@RequestMapping("/regist") //当类和类里的方法里都有注解映射时，路径应该是   类映射名-->方法映射名   ，先访问类映射在找方法映射
	public ModelAndView regist(@ModelAttribute(name="userName")String userName,String pwd) {  //直接可以从页面获取到需要的数据,HttpServletRequest request,在里面传入，然后取出request.setAttribute("userName", userName)，在页面动态显示
		System.out.println(userName + ":" + pwd);
		//System.out.println("    注册       ");
		ModelAndView mv = new ModelAndView();		
		boolean ok = this.UserService.registUser(userName, pwd);		
		if(ok) {
			mv.setViewName("/RegistOk");
			//mv.addObject("userName", userName);//此方法的作用和入参里的@ModelAttribute(name="userName")效果相同，可以将需要的内容动态显示到页面上
						
			//request.setAttribute("userName", userName); //成功或失败后跳转页面，动态显示返回信息		
			//return "/RegistOk";
		}else {
			mv.setViewName("/Regist");
			mv.addObject("msg", "注册失败");
						
			//RequestMapping.setAttribute("msg","注册失败，请重试");
			//return "/Regist";
		}
		return mv;
	}
	
	

	/** Alt+shift+j
	 * 登录
	 * @param mv
	 * @param user
	 * @param session
	 * @param auto
	 * @param response
	 * @param referer
	 * @return
	 */
	@RequestMapping(path="/login" , params = {"userName","pwd"})//指明请求头，将请求头里的Referer 赋值给referer，required = false表示当前值可有可无
	public ModelAndView login(ModelAndView mv,User user, HttpSession session,boolean auto, HttpServletResponse response,@RequestHeader(name = "Referer", required = false) String referer) {
		if("http://localhost:8088/login.do".equals(referer) || "http://localhost:8088/Login.jsp".equals(referer)) {	 //判断用户的referer是否是从指定的域名发送过来的，
		User u = this.UserService.login(user);
			if(null == u) {
				mv.addObject("msg", "用户名或密码不正确。");
				mv.setViewName("/Login");
			}else {
				session.setAttribute("userInfo", u);
				
				//返回一个blog对象，将blog放入session中，起名blogInfo
				Blog blog = this.blogService.findByUserID(u.getUserID());
				session.setAttribute("blogInfo", blog);
				
				//在页面上复选框打勾可能没有值，所以需要时对象类型，设置自动登录时的Cookie
				if(auto) {
					//创建两个Cookie放入用户名和密码
					Cookie c = new Cookie("userName", u.getUserName());
					c.setMaxAge(10*24*60*60); //设置Cookie的保存时间
					
					Cookie c2 = new Cookie("pwd", u.getPwd());
					c2.setMaxAge(10*24*60*60);
					
					response.addCookie(c);
					response.addCookie(c2);
				}
					
				mv.setViewName("redirect:/"); //重定向到首页
			}		
		}else {
			mv.setViewName("redirect:/Login.jsp");
			mv.addObject("msg", "别瞎玩！！！！！！！");//加消息
			
		}
		return mv;
	}
	
	
	//注销用户，删除session里用户的数据，删除浏览器里Cookie里的数据
	@GetMapping("/logout")
	public String Logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("userInfo"); //可以用remover方法单独删除session的key
		session.removeAttribute("blogInfo");	
		session.invalidate();  //或者用invalidate放法直接清除
		
		Cookie c1 = new Cookie("userName", "");
		c1.setMaxAge(0);
		Cookie c2 = new Cookie("pwd", "");
		c2.setMaxAge(0);
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "redirect:/";
	}
	
	
	//返回的是view
	@RequestMapping("/index")
	public String index() {
		return "/Index";
	}
}
