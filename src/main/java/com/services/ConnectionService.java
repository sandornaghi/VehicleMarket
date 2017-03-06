package com.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * In this class a connection to the Elasticsearch search engine.
 * The connection is Produced, and is Injected in the caller class.
 * The connection is closed automatically.
 * @author sandor.naghi
 */
public class ConnectionService {

	private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());

	@Produces
	private TransportClient connectionsToElasticsearch() {
		TransportClient client = null;

		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			LOGGER.severe(e.getMessage());
		}

		return client;
	}

	@SuppressWarnings("unused")
	private void closeConnection(@Disposes TransportClient client) {
		if (client != null) {
			client.close();
		}
	}

}
