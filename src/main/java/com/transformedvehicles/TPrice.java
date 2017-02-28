package com.transformedvehicles;

import com.vsevehiclebeans.Price;

public class TPrice {

	private long basePrice;

	private String currency;

	public TPrice() {

	}

	public TPrice(Price price) {
		this.basePrice = price.getBasePrice();
		this.currency = price.getCurrency();
	}

	public long getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(long basePrice) {
		this.basePrice = basePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "TPrice [basePrice=" + basePrice + ", currency=" + currency + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (basePrice ^ (basePrice >>> 32));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
		TPrice other = (TPrice) obj;
		if (basePrice != other.basePrice)
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		return true;
	}

}
