package mos.edu.server.fancinema;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import mos.edu.server.fancinema.config.DatabaseConfig;
import mos.edu.server.fancinema.config.WebConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		final Class<?>[] rootConfigClasses = {
			DatabaseConfig.class
		};
		return rootConfigClasses;
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		final Class<?>[] servletConfigClasses = {
			WebConfig.class
		};
		return servletConfigClasses;
	}
	
	@Override
	protected String[] getServletMappings() {
		final String[] servletMappings = {
			Constants.URI.HOME
		};
		return servletMappings;
	}

}
