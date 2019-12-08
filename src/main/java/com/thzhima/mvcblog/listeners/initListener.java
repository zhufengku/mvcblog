package com.thzhima.mvcblog.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class initListener
 *
 */
@WebListener
public class initListener implements ServletContextListener {

	
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent evt)  { 
        ServletContext app = evt.getServletContext();
        String rootPath = app.getContextPath();
        app.setAttribute("contextPath", rootPath);
    }

    
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent evt)  { 
         // TODO Auto-generated method stub
    }


    
	
}
