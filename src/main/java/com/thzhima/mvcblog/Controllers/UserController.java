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

//���Ʋ㷽��

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

	
	//ע��
	@RequestMapping("/regist") //���������ķ����ﶼ��ע��ӳ��ʱ��·��Ӧ����   ��ӳ����-->����ӳ����   ���ȷ�����ӳ�����ҷ���ӳ��
	public ModelAndView regist(@ModelAttribute(name="userName")String userName,String pwd) {  //ֱ�ӿ��Դ�ҳ���ȡ����Ҫ������,HttpServletRequest request,�����洫�룬Ȼ��ȡ��request.setAttribute("userName", userName)����ҳ�涯̬��ʾ
		System.out.println(userName + ":" + pwd);
		//System.out.println("    ע��       ");
		ModelAndView mv = new ModelAndView();		
		boolean ok = this.UserService.registUser(userName, pwd);		
		if(ok) {
			mv.setViewName("/RegistOk");
			//mv.addObject("userName", userName);//�˷��������ú�������@ModelAttribute(name="userName")Ч����ͬ�����Խ���Ҫ�����ݶ�̬��ʾ��ҳ����
						
			//request.setAttribute("userName", userName); //�ɹ���ʧ�ܺ���תҳ�棬��̬��ʾ������Ϣ		
			//return "/RegistOk";
		}else {
			mv.setViewName("/Regist");
			mv.addObject("msg", "ע��ʧ��");
						
			//RequestMapping.setAttribute("msg","ע��ʧ�ܣ�������");
			//return "/Regist";
		}
		return mv;
	}
	
	

	/** Alt+shift+j
	 * ��¼
	 * @param mv
	 * @param user
	 * @param session
	 * @param auto
	 * @param response
	 * @param referer
	 * @return
	 */
	@RequestMapping(path="/login" , params = {"userName","pwd"})//ָ������ͷ��������ͷ���Referer ��ֵ��referer��required = false��ʾ��ǰֵ���п���
	public ModelAndView login(ModelAndView mv,User user, HttpSession session,boolean auto, HttpServletResponse response,@RequestHeader(name = "Referer", required = false) String referer) {
		if("http://localhost:8088/login.do".equals(referer) || "http://localhost:8088/Login.jsp".equals(referer)) {	 //�ж��û���referer�Ƿ��Ǵ�ָ�����������͹����ģ�
		User u = this.UserService.login(user);
			if(null == u) {
				mv.addObject("msg", "�û��������벻��ȷ��");
				mv.setViewName("/Login");
			}else {
				session.setAttribute("userInfo", u);
				
				//����һ��blog���󣬽�blog����session�У�����blogInfo
				Blog blog = this.blogService.findByUserID(u.getUserID());
				session.setAttribute("blogInfo", blog);
				
				//��ҳ���ϸ�ѡ��򹴿���û��ֵ��������Ҫʱ�������ͣ������Զ���¼ʱ��Cookie
				if(auto) {
					//��������Cookie�����û���������
					Cookie c = new Cookie("userName", u.getUserName());
					c.setMaxAge(10*24*60*60); //����Cookie�ı���ʱ��
					
					Cookie c2 = new Cookie("pwd", u.getPwd());
					c2.setMaxAge(10*24*60*60);
					
					response.addCookie(c);
					response.addCookie(c2);
				}
					
				mv.setViewName("redirect:/"); //�ض�����ҳ
			}		
		}else {
			mv.setViewName("redirect:/Login.jsp");
			mv.addObject("msg", "��Ϲ�棡������������");//����Ϣ
			
		}
		return mv;
	}
	
	
	//ע���û���ɾ��session���û������ݣ�ɾ���������Cookie�������
	@GetMapping("/logout")
	public String Logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("userInfo"); //������remover��������ɾ��session��key
		session.removeAttribute("blogInfo");	
		session.invalidate();  //������invalidate�ŷ�ֱ�����
		
		Cookie c1 = new Cookie("userName", "");
		c1.setMaxAge(0);
		Cookie c2 = new Cookie("pwd", "");
		c2.setMaxAge(0);
		
		response.addCookie(c1);
		response.addCookie(c2);
		
		return "redirect:/";
	}
	
	
	//���ص���view
	@RequestMapping("/index")
	public String index() {
		return "/Index";
	}
}
