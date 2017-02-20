package com.vehiclebeans;

import java.util.HashMap;
import java.util.Map;

public class Country {

	private Map<Integer, String> country;
	
	public Country() {
		country = new HashMap<>();
		country.put(571, "CH");
		country.put(572, "DE");
		country.put(573, "FR");
	}
	
	public String getCountry(int code) {
		String result = country.get(code);
		if (result == null) {
			result = "EU";
		}
		return result;
	}
	
	public void addCountry(Integer key, String value) {
		country.putIfAbsent(key, value);
	}

	@Override
	public String toString() {
		return "Country [country=" + country + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		Country other = (Country) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}
	
}
