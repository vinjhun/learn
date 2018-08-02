package com.example.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements ApplicationContextAware{

	public ApplicationContext ac;
	
	public <T> T getBean(String className) {
		@SuppressWarnings("unchecked")
		T t = (T)ac.getBean(className);
		return t;
	} 
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.ac = arg0;
	}

}
