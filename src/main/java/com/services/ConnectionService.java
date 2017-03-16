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

	@Produces
	private TransportClient connectionsToElasticsearch() {
		TransportClient client = null;

		String[] array = getHostAndPortnumber();
		String host = array[0];
		int port = Integer.parseInt(array[1]);

		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		} catch (UnknownHostException e) {
			LOGGER.severe(e.getMessage());
		}

		return client;
	}

	@SuppressWarnings("unused")
	private void closeConnection(@Disposes TransportClient client) {
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

		String[] array = new String[2];

		Properties prop = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(input);

			array[0] = prop.getProperty("host");
			array[1] = prop.getProperty("port");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

		return array;
	}
}
