package com.scheduler;

import java.util.TimerTask;
import java.util.logging.Logger;

import com.services.ImportService;

public class ImportTimer extends TimerTask{

	private static final Logger LOGGER = Logger.getLogger(ImportTimer.class.getName());
	
//	@Inject
//	private VehicleRestService vehicleRest;
	
	public ImportTimer() {
		
	}
	
	public void run() {
		try {
			System.out.println("Start");
			//vehicleRest.importVehiclesFromVSE("de", "new");
			ImportService importService = new ImportService();
			importService.importVseVehicle("de", "new");
			System.out.println("Stop");
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
		}
	}
}
