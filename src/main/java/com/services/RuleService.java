package com.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rulebeans.Configurations;

public class RuleService {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicle-rules");

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction tx = em.getTransaction();

	public void persistRule(Configurations sysConfig) {

		tx.begin();
		em.persist(sysConfig);
		tx.commit();
	}
}
