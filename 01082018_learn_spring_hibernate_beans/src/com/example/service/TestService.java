package com.example.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ITestDao;

@Transactional
public class TestService implements ITestService,ApplicationContextAware {

	public ApplicationContext ac;
	
	@Override
	public void findTest() {
		ITestDao test = (ITestDao)ac.getBean("testDao");
		
		test.findTest();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.ac = arg0;
	}

	@Override
	public <T> void createTest(T t) {
		ITestDao test = (ITestDao)ac.getBean("testDao");
		
		test.createTest(t);
	}

	@Override
	public <T> void batchCreateTest(List<T> t) {
		ITestDao test = (ITestDao)ac.getBean("testDao");
		
		test.batchCreateTest(t);
		
	}
}
