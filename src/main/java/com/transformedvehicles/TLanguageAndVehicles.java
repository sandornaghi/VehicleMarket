package com.transformedvehicles;

import java.util.List;

public class TLanguageAndVehicles {

	private String language;

	private List<TVehicle> tVehicleList;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<TVehicle> gettVehicleList() {
		return tVehicleList;
	}

	public void settVehicleList(List<TVehicle> tVehicleList) {
		this.tVehicleList = tVehicleList;
	}

	@Override
	public String toString() {
		return "TLanguage [language=" + language + ", tVehicleList=" + tVehicleList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((tVehicleList == null) ? 0 : tVehicleList.hashCode());
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
		TLanguageAndVehicles other = (TLanguageAndVehicles) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (tVehicleList == null) {
			if (other.tVehicleList != null)
				return false;
		} else if (!tVehicleList.equals(other.tVehicleList))
			return false;
		return true;
	}
}
