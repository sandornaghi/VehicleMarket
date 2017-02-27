package com.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;

@Transactional
public class RuleService {

	@PersistenceContext(unitName = "vehicle-rules")
	private EntityManager em;

	public List<Configuration> getConfigurationRules(String country, String vehicleCategory) {

		TypedQuery<Configuration> query = em.createNamedQuery("ConfigurationRules", Configuration.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);

		List<Configuration> confList = query.getResultList();

		return confList;
	}

	public List<Correction> getCorrectionRules(String country, String vehicleCategory) {

		TypedQuery<Correction> query = em.createNamedQuery("CorrectionRules", Correction.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);

		List<Correction> corrList = query.getResultList();

		return corrList;
	}

}
