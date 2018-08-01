package com.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.ITestService;

public class App
{
    public static void main(String[] args)
    {
        //(1). try passing in configuration inside - simple test without servlet
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigTest.class);
        
        //localcontainerentitymanagerfactorybean is using jdklib, and require to case to its parent
        ITestService ts = (ITestService)ac.getBean("testService");
        
        ts.findTest();
        ts.insertTest();
    }
}
