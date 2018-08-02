package com.example.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.entity.EntitlementDetailEntity;
import com.example.entity.Test3Entity;
import com.example.service.ITestService;

public class App {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		ITestService test = (ITestService)ac.getBean("testService");
		
//		ITestDao testDao = (ITestDao)ac.getBean("testDao");
		
//		testDao.findTest();
		test.findTest();
		
		//create test
		Test3Entity entity1 = new Test3Entity();
		entity1.setId("5");
		
		EntitlementDetailEntity entity = new EntitlementDetailEntity();
		entity.setId("99999999999999999999999999999999999999999999999999999999999999999999999999");
		entity.setCustomerRate(new BigDecimal("10.00"));
		entity.setProductRate(new BigDecimal("10.00"));
		entity.setProfitRate(new BigDecimal("10.00"));
		entity.setSpecialRateFlag("abcdefg");
		
		List<Object> testList = new ArrayList<Object>();				
		testList.add(entity1);
		testList.add(entity);
		
		test.batchCreateTest(testList);
		
	}
	
	
}
