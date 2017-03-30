package com.esfacets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInput {

	@JsonProperty
	private String key;

	@JsonProperty
	private String value;

	@JsonProperty
	private long minPrice;

	@JsonProperty
	private long maxPrice;

	@JsonProperty
	private String minDate;

	@JsonProperty
	private String maxDate;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(long minPrice) {
		this.minPrice = minPrice;
	}

	public long getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(long maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((maxDate == null) ? 0 : maxDate.hashCode());
		result = prime * result + (int) (maxPrice ^ (maxPrice >>> 32));
		result = prime * result + ((minDate == null) ? 0 : minDate.hashCode());
		result = prime * result + (int) (minPrice ^ (minPrice >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (maxDate == null) {
			if (other.maxDate != null)
				return false;
		} else if (!maxDate.equals(other.maxDate))
			return false;
		if (maxPrice != other.maxPrice)
			return false;
		if (minDate == null) {
			if (other.minDate != null)
				return false;
		} else if (!minDate.equals(other.minDate))
			return false;
		if (minPrice != other.minPrice)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}