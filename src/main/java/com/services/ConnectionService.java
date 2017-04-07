package com.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * In this class a connection to the Elasticsearch search engine. The connection
 * is Produced, and is Injected in the caller class. The connection is closed
 * automatically.
 * 
 * @author sandor.naghi
 */
public class ConnectionService {

	private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());
	
	private static final String HOST_NAME = "elasticsearchHost";
	private static final String PORT_NUMBER = "elasticsearchPort";
	private static final String CONFIG = "config.properties";

	@Produces
	private TransportClient createConnectionToES() {
		TransportClient client = null;

		String[] esConnectionProperties = getHostAndPortnumber();
		String esHost = esConnectionProperties[0];
		int esPort = Integer.parseInt(esConnectionProperties[1]);

		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
		} catch (UnknownHostException e) {
			LOGGER.severe(e.getMessage());
		}

		return client;
	}

	@SuppressWarnings("unused")
	private void closeConnectionToES(@Disposes TransportClient client) {
		if (client == null) {
			return;
		}

		try {
			client.close();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
	}

	private String[] getHostAndPortnumber() {
	
		String[] esConProp = new String[2];

		Properties prop = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream(CONFIG);
		try {
			prop.load(input);

			esConProp[0] = prop.getProperty(HOST_NAME); 
			esConProp[1] = prop.getProperty(PORT_NUMBER);
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

		return esConProp;
	}
}
