package mos.edu.server.fancinema.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("mos.edu.server.fancinema")
public class WebConfig extends WebMvcConfigurerAdapter {
	
}
