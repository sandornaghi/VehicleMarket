package com.vehiclebeans;

import java.util.HashMap;
import java.util.Map;

public class BodyType {

	private Map<Integer, String> bodyType;
	
	public BodyType() {
		bodyType = new HashMap<>();
		bodyType.put(1, "Limousine");
		bodyType.put(2, "Coupe");
		bodyType.put(3, "Cabriolet/Roadster");
		bodyType.put(4, "MPV");
		bodyType.put(5, "Crossover");
		bodyType.put(6, "SUV");
		bodyType.put(7, "Roadster");
	}
	
	public String getBodyType(int code) {
		String result = bodyType.get(code);
		if (result == null) {
			result = "Car";
		}
		
		return result;
	}
	
	public void addBodyType(int key, String value) {
		bodyType.putIfAbsent(key, value);
	}

	@Override
	public String toString() {
		return "BodyType [bodyType=" + bodyType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
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
		BodyType other = (BodyType) obj;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		return true;
	}
	
}
