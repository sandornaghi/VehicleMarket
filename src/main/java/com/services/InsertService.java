package com.services;

import org.elasticsearch.client.transport.TransportClient;

import com.transformedvehicles.TVehicles;

public class InsertService {

	public void insertVehicle(String country, TVehicles tVehicles, TransportClient client) {	//rename, fara language 

//		for (TLanguageAndVehicles langVehicle : tVehicles.getVehicleList()) {
//			for (TVehicle vehicle : langVehicle.gettVehicleList()) {
//				String index = country.toLowerCase() + "_" + tVehicles.getVehicleCategory().toLowerCase() + "_"
//						+ vehicle.getLanguage() + "_" + DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
//
//				String id = vehicle.getId();
//
//				client.prepareIndex(index, "vehicle", id).setSource(new Gson().toJson(vehicle)).get();
//			}
//		}

	}
}
