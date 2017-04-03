package com.esfacets.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInput {

	@JsonProperty
	private String query;

	@JsonProperty
	private List<String> language;

	@JsonProperty
	private List<String> bodyType;

	@JsonProperty
	private List<String> paint;

	@JsonProperty
	private List<String> fuelType;

	@JsonProperty
	private List<String> transmission;

	private PriceInformation priceInformation;

	private FirstRegistrationDate firstRegistrationDate;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String> getLanguage() {
		return language;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	public List<String> getBodyType() {
		return bodyType;
	}

	public void setBodyType(List<String> bodyType) {
		this.bodyType = bodyType;
	}

	public List<String> getPaint() {
		return paint;
	}

	public void setPaint(List<String> paint) {
		this.paint = paint;
	}

	public List<String> getFuelType() {
		return fuelType;
	}

	public void setFuelType(List<String> fuelType) {
		this.fuelType = fuelType;
	}

	public List<String> getTransmission() {
		return transmission;
	}

	public void setTransmission(List<String> transmission) {
		this.transmission = transmission;
	}

	public PriceInformation getPriceInformation() {
		return priceInformation;
	}

	public void setPriceInformation(PriceInformation priceInformation) {
		this.priceInformation = priceInformation;
	}

	public FirstRegistrationDate getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(FirstRegistrationDate firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result + ((firstRegistrationDate == null) ? 0 : firstRegistrationDate.hashCode());
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((paint == null) ? 0 : paint.hashCode());
		result = prime * result + ((priceInformation == null) ? 0 : priceInformation.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
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
		UserInput other = (UserInput) obj;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		if (firstRegistrationDate == null) {
			if (other.firstRegistrationDate != null)
				return false;
		} else if (!firstRegistrationDate.equals(other.firstRegistrationDate))
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (paint == null) {
			if (other.paint != null)
				return false;
		} else if (!paint.equals(other.paint))
			return false;
		if (priceInformation == null) {
			if (other.priceInformation != null)
				return false;
		} else if (!priceInformation.equals(other.priceInformation))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		return true;
	}
}