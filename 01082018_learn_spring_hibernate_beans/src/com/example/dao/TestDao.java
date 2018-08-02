package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("testDao")
public class TestDao implements ITestDao {

// entity manager can only be injected inside a ejb
//	public EntityManagerFactory emf;
	
//	public void setEmf(EntityManagerFactory emf) {
//	this.emf = emf;
//}

	/***************************POINT CUT*********************************/
	
	// this type of annotation allow shared entity manager across all dao
	// having type of EXTENDED && TRANSACTIONAL
	// EXTENDED is not shared among dao / not thread safe
	@PersistenceContext
	public EntityManager em;

	@Override
	public void findTest() {
//		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select a from EntitlementDetailEntity a ");
		System.out.println(q.getResultList());
	}

	@Override
	public <T> void createTest(T t) {
		System.out.println(t);
		em.persist(t);
	}

	@Override
	public <T> void batchCreateTest(List<T> t) {
		
		for(Object test : t)
		{
			System.out.println(t);
			em.persist(test);
		}
	}
}
