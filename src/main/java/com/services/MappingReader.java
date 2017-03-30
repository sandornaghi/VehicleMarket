package com.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This class read the mapping needed for the vehicle type in Elasticsearch,
 * from resource folder.
 * 
 * @author sandor.naghi
 *
 */
public class MappingReader {

	private static final Logger LOGGER = Logger.getLogger(MappingReader.class.getName());

	private static final String LINE_SEPARATOR = "line.separator";

	private static final String RESOURCE_NAME = "esResources\\vehicleMapping.json";

	public String getMapping() {

		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(RESOURCE_NAME).getFile());

		StringBuilder fileContent = new StringBuilder((int) file.length());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			String lineSeparator = System.getProperty(LINE_SEPARATOR);
			while (scanner.hasNextLine()) {
				fileContent.append(scanner.nextLine() + lineSeparator);
			}

		} catch (FileNotFoundException e) {
			LOGGER.severe(e.getMessage());
		} finally {
			scanner.close();
		}

		return fileContent.toString();
	}
}
