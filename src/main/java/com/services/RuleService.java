package com.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.rulebeans.Configurations;

@Transactional
public class RuleService {

	@PersistenceContext(unitName = "vehicle-rules")
	private EntityManager em ;

	public void persistRule(Configurations sysConfig) {

		em.persist(sysConfig);
	}
}
