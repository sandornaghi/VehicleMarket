package com.vehiclebeans;

import java.util.HashMap;
import java.util.Map;

public class Transmission {

	private Map<Integer, String> transmission;

	public Transmission() {
		transmission = new HashMap<>();
		transmission.put(1, "Manual");
		transmission.put(1, "Semi automatic");
		transmission.put(1, "Automatic");
	}

	public String getTransmissionType(int code) {
		String result = transmission.get(code);

		if (result == null) {
			result = "Unknown!";
		}

		return result;
	}

	public void addTransmissionType(int key, String value) {
		transmission.putIfAbsent(key, value);
	}

	@Override
	public String toString() {
		return "Transmission [transmission=" + transmission + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
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
		Transmission other = (Transmission) obj;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		return true;
	}

}
