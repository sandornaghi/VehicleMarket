package com.services;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import org.elasticsearch.client.transport.TransportClient;

//import com.google.gson.Gson;
//import com.transformedvehicles.TLanguageAndVehicles;
//import com.transformedvehicles.TVehicle;
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
