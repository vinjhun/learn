package com.example.demo.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{

	final String characterEncoding_UTF_8 = "UTF-8";
	//the return type always be interface
	//template resolver
		//servlet context
		//class loader
		//spring resource
		//file 
	//template engine
	//view resolver
//	@Bean
//	@Description("ServletContextTemplateResolver")
//	public ITemplateResolver templateResolver(ServletContext sc) {
//		
//		ServletContextTemplateResolver tr = new ServletContextTemplateResolver(sc);
//		tr.setTemplateMode(TemplateMode.HTML);
//		tr.setPrefix("/WEB-INF/views/");	//for servlet context is in web-inf
//		tr.setCharacterEncoding(characterEncoding_UTF_8);
//		tr.setSuffix(".html");
//		
//		
//		System.out.println("test context path	:" + sc.getContextPath());
//		
//		return tr;
//	}
	
//	@Bean
//	@Description("class loader template resolver")
//	public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
//		
//		ClassLoaderTemplateResolver tr = new ClassLoaderTemplateResolver();
//		tr.setTemplateMode(TemplateMode.HTML);
//		tr.setPrefix("templates/thymeleaf/");	//for class loader , it put at resources
//		tr.setCharacterEncoding(characterEncoding_UTF_8);
//		tr.setSuffix(".html");
//		
//		return tr;
//	}
	
	@Bean
	@Description("ServletContextTemplateResolver")
	public ITemplateResolver templateResolver(ServletContext sc) {
		
		SpringResourceTemplateResolver tr = new SpringResourceTemplateResolver();
		
		tr.setTemplateMode(TemplateMode.HTML);
		tr.setPrefix("templates/thymeleaf/");		//for maven, it reside in webapps
		tr.setSuffix(".html");
		tr.setCacheable(false);
		tr.setCharacterEncoding(characterEncoding_UTF_8);
		
		return tr;
	}
	
	@Bean
	@Description("Template Engine for thymeleaf and spring boot")
	public SpringTemplateEngine templateEngine(ServletContext sc) {
		
		SpringTemplateEngine se = new SpringTemplateEngine();
		se.setTemplateResolver(templateResolver(sc));
		
		return se;
		
	}
	
	@Bean
	@Description("view resolver -> to translate the thymeleaf ")
	public ViewResolver viewResolver(ServletContext sc) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		String[] test = {"*.html",".xhmtl"};
		
		viewResolver.setTemplateEngine(templateEngine(sc));
		viewResolver.setViewNames(test);
		viewResolver.setCharacterEncoding(characterEncoding_UTF_8);
		
		return viewResolver;
	}
	
	
	/**
	 * Because spring boot cant recognize the folder in static, it requires the mapping
	 * **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		
		super.addResourceHandlers(registry);
	}

}
