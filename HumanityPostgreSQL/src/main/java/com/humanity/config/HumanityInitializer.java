package com.humanity.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;

public class HumanityInitializer implements WebApplicationInitializer{
	
	
	    private static final Logger logger = Logger.getLogger(HumanityInitializer.class);
	
	    @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
	        AnnotationConfigWebApplicationContext context = getContext();
	        context.register(HumanityConfig.class);
	        servletContext.addListener(new ContextLoaderListener(context));
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/*");
	        dispatcher.addMapping("/Humanity/");
	    }

	    private AnnotationConfigWebApplicationContext getContext() {
	        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	        context.setConfigLocation("com.humanity");
	        return context;
	    }

}
