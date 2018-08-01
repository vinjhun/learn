package com.example.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BaseDao;

@Service
@Transactional
public class TestService implements ITestService,ApplicationContextAware
{
    @Autowired
    public ApplicationContext ac;
    
    @Override
    public void insertTest()
    {
        BaseDao bd = (BaseDao)ac.getBean("baseDao");
        System.out.println(bd);
        bd.insertTest();
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException
    {
       this.ac = arg0;
    }

    @Override
    public void findTest()
    {
        BaseDao bd = (BaseDao)ac.getBean("baseDao");
        System.out.println(bd);
        bd.findTest();
    }
    
    
}
