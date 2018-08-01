package com.example.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.entity.EntitlementDetailsEntity;

@Repository
public class BaseDao
{
    /**
     * Entity Manager is an interface
     * 
     **/
//    @PersistenceUnit(unitName = "entityManagerFactory")
    //why entitymanager factory cannot perform ?
    @PersistenceContext
    public EntityManager em;

    public void setEm(EntityManager em)
    {
        this.em = em;
    }

    public void findTest()
    {
        Query query = em.createQuery("select u from EntitlementDetailsEntity u");
        System.out.println(query.getResultList());
    }

    public void insertTest()
    {
        // TODO : how to obtain all entity classes and pass into here for persist?
        EntitlementDetailsEntity newEntity = new EntitlementDetailsEntity();
//        newEntity.setId("8");
        newEntity.setProductRate(new BigDecimal("2.00"));
        newEntity.setProfitRate(new BigDecimal("3.00"));
        newEntity.setSpecialRateFlag("Y");

        em.persist(newEntity);
    }

}
