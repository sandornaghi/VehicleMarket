package com.connection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class CreateConnection {

	private static final Logger LOGGER = Logger.getLogger(CreateConnection.class.getName());

	@Produces
	public TransportClient createConnection() {
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
