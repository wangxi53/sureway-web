package ca.hansolutions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter{
	@Bean
	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver iRVR = new InternalResourceViewResolver();
		iRVR.setPrefix("/WEB-INF/views");
		iRVR.setSuffix(".jsp");
		return iRVR;

	}
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
