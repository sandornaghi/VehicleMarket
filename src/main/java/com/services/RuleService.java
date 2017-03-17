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

	private static final String CONFIGURATION_RULES = "ConfigurationRules";
	private static final String CORRECTION_RULES = "CorrectionRules";
	private static final String ACCEPTED_LANGUAGES = "AcceptedLanguages";
	private static final String CONTEXT_HAS_RULES = "ContextHasRules";
	private static final String IMPORT_TIME_INTERVAL = "ImportTimeInterval";
	
		
	private static final String COUNTRY = "country";
	private static final String VEHICLE_CATEGORY = "vehicleCategory";
	private static final String LANGUAGE = "language";
	
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

		TypedQuery<Configuration> query = em.createNamedQuery(CONFIGURATION_RULES, Configuration.class);
		query.setParameter(COUNTRY, country);
		query.setParameter(VEHICLE_CATEGORY, vehicleCategory);

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

		TypedQuery<Correction> query = em.createNamedQuery(CORRECTION_RULES, Correction.class);
		query.setParameter(COUNTRY, country);
		query.setParameter(VEHICLE_CATEGORY, vehicleCategory);
		query.setParameter(LANGUAGE, language);

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

		TypedQuery<String> query = em.createNamedQuery(ACCEPTED_LANGUAGES, String.class);
		query.setParameter(COUNTRY, country);
		query.setParameter(VEHICLE_CATEGORY, vehicleCategory);

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

		TypedQuery<String> query = em.createNamedQuery(CONTEXT_HAS_RULES, String.class);
		query.setParameter(COUNTRY, country);
		query.setParameter(VEHICLE_CATEGORY, vehicleCategory);
		query.setParameter(LANGUAGE, language);

		String res = null;
		try {
			res = query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.severe(e.getMessage());
		}

		return res != null;
	}

	/**
	 * Read the time when the import will be done automatically.
	 * 
	 * @return List of Configuration objects, that contain country, vehicle
	 *         category and the time.
	 */
	public List<Configuration> getImportTimeInterval() {

		TypedQuery<Configuration> query = em.createNamedQuery(IMPORT_TIME_INTERVAL, Configuration.class);

		return query.getResultList();
	}
}
