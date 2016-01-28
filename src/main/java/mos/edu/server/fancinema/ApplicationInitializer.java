package mos.edu.server.fancinema;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import mos.edu.server.fancinema.config.DatabaseConfig;
import mos.edu.server.fancinema.config.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		Class<?>[] configClasses = {
			WebConfig.class,
			DatabaseConfig.class
		};
		return configClasses;
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		String[] servletMappings = {
			"/"
		};
		return servletMappings;
	}

}
