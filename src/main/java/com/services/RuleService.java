package com.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.rulebeans.Configurations;
import com.rulebeans.Corrections;
import com.rulebeans.Rules;

@Transactional
public class RuleService {

	@PersistenceContext(unitName = "vehicle-rules")
	private EntityManager em;

	public void persistRule(Rules sysConfig) {

		em.persist(sysConfig);
	}

	public List<Configurations> getConfigurationRules(String country, String vehicleCategory) {
		List<Configurations> confList = null;

		TypedQuery<Configurations> query = em.createNamedQuery("ConfigurationRules", Configurations.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);
		confList = query.getResultList();

		return confList;
	}

	public List<Corrections> getCorrectionRules(String country, String vehicleCategory) {
		List<Corrections> corrList = null;

		TypedQuery<Corrections> query = em.createNamedQuery("CorrectionRules", Corrections.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);
		corrList = query.getResultList();

		return corrList;
	}
}
