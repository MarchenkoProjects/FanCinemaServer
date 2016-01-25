package mos.edu.server.fancinema;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import mos.edu.server.fancinema.config.WebConfig;

public class ApplicationInitializer implements WebApplicationInitializer {
	private static final String DISPATCHER = "dispatcher";
	private static final String MAPPING = "/";
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = 
				new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		servletContext.addListener(new ContextLoaderListener(context));
		
		ServletRegistration.Dynamic servlet = 
				servletContext.addServlet(DISPATCHER, new DispatcherServlet(context));
		servlet.addMapping(MAPPING);
		servlet.setLoadOnStartup(1);
	}

}
