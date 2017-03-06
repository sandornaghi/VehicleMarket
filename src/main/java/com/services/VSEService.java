package com.services;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.vsevehiclebeans.Vehicles;

/**
 * In this class we set up the name of the file, from where we read the vehicles.
 * @author sandor.naghi
 *
 */
public class VSEService {

	public static final Logger LOGGER = Logger.getLogger(VSEService.class.getName());

	/**
	 * Read the specified file from the resources.
	 * @param country	The market from where we do the import.
	 * @param category	The category of the vehicle, new or used.
	 * @return	The vehicles for the given country and category.
	 * @throws JAXBException
	 */
	public Vehicles getVehiclesFromVSE(String country, String category) throws JAXBException {

		String fileName = buildFilename(country, category);
		Vehicles vehicles = null;

		ClassLoader classLoader = getClass().getClassLoader();
		File file = null;

		try {
			file = new File(classLoader.getResource(fileName).getFile());
		} catch (NullPointerException e) {
			LOGGER.severe(e.getMessage());
		}

		if (file != null) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Vehicles.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			vehicles = (Vehicles) unmarshaller.unmarshal(file);
		}

		return vehicles;
	}

	private String buildFilename(String country, String category) {

		StringBuilder sb = new StringBuilder();

		sb.append("vseResources\\vse_vehicles_");

		sb.append(country.toUpperCase());
		sb.append("_");
		sb.append(category.toUpperCase());
		sb.append(".xml");

		return sb.toString();
	}
}
