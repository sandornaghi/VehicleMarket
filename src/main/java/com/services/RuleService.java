package com.services;

import java.util.ArrayList;
/**
 * 
 */
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;

/**
 * In this class SQL query's are executed on the "sys_config" and
 * "translations_corections" tables.
 * 
 * @author sandor.naghi
 *
 */
@Transactional
public class RuleService {

	private static final Logger LOGGER = Logger.getLogger(RuleService.class.getName());

	@PersistenceContext(unitName = "vehicle-rules")
	private EntityManager em;

	/**
	 * Read all configuration rules from the DB.
	 * 
	 * @param country
	 *            Name of the country.
	 * @param vehicleCategory
	 *            Category of vehicle, new or used.
	 * @return List of configurations, that will be applied on the vehicle.
	 */
	public List<Configuration> getConfigurationRules(String country, String vehicleCategory) {

		TypedQuery<Configuration> query = em.createNamedQuery("ConfigurationRules", Configuration.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);

		List<Configuration> confList = query.getResultList();

		return confList;
	}

	/**
	 * Read all correction rules from the DB.
	 * 
	 * @param country
	 *            Name of the country.
	 * @param vehicleCategory
	 *            Category of vehicle, new or used.
	 * @return List of corrections, that will be applied on the Vehicle.
	 */
	public List<Correction> getCorrectionRules(String country, String vehicleCategory, String language) {

		TypedQuery<Correction> query = em.createNamedQuery("CorrectionRules", Correction.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);
		query.setParameter("language", language);

		List<Correction> corrList = query.getResultList();

		return corrList;
	}

	/**
	 * Read all the languages that are accepted for different market.
	 * 
	 * @param country
	 *            Name of the country.
	 * @param vehicleCategory
	 *            Category of vehicle, new or used.
	 * @return The accepted languages for the market.
	 */
	public List<String> acceptedLanguages(String country, String vehicleCategory) {

		TypedQuery<String> query = em.createNamedQuery("AcceptedLanguages", String.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);

		String res = null;
		try {
			res = query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.severe(e.getMessage());
		}

		List<String> languageList = new ArrayList<>();
		if (res != null) {
			String[] languages = res.split(",");

			if (languages != null) {
				for (String s : languages) {
					languageList.add(s.trim());
				}
			}
		}

		return languageList;
	}

	/**
	 * Verify if the country and vehicle category has rules for this language.
	 * 
	 * @param country
	 *            Name of the country.
	 * @param vehicleCategory
	 *            Category of vehicle, new or used.
	 * @param language
	 * @return True if the context has rules to apply, otherwise false.
	 */
	public boolean hasRules(String country, String vehicleCategory, String language) {

		TypedQuery<String> query = em.createNamedQuery("ContextHasRules", String.class);
		query.setParameter("country", country);
		query.setParameter("vehicleCategory", vehicleCategory);
		query.setParameter("language", language);

		String res = null;
		try {
			res = query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.severe(e.getMessage());
		}

		return res != null;
	}
}
