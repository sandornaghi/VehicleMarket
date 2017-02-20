package com.vehiclebeans;

import java.util.HashMap;
import java.util.Map;

public class FuelType {

	private Map<Integer, String> fuelType;
	
	public FuelType() {
		fuelType = new HashMap<>();
		fuelType.put(1, "Electric");
		fuelType.put(2, "Hybrid");
		fuelType.put(3, "Electric");
		fuelType.put(4, "Petrol");
		fuelType.put(5, "Diesel");
	}
	
	public String getFuelType(int code) {
		String result = fuelType.get(code);
		
		if (result == null) {
			result = "Unknown!";
		}
		
		return result;
	}
	
	public void addFuelType(Integer key, String value) {
		fuelType.putIfAbsent(key, value);
	}

	@Override
	public String toString() {
		return "FuelType [fuelType=" + fuelType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuelType other = (FuelType) obj;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		return true;
	}
	
}
