package com.response;

import com.vsevehiclebeans.Vehicles;

public class VehicleResponse {

	private ResponseCodeAndDescription respCodeDesc;
	private Vehicles vehicles;

	public ResponseCodeAndDescription getRespCodeDesc() {
		return respCodeDesc;
	}

	public void setRespCodeDesc(ResponseCodeAndDescription respCodeDesc) {
		this.respCodeDesc = respCodeDesc;
	}

	public Vehicles getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((respCodeDesc == null) ? 0 : respCodeDesc.hashCode());
		result = prime * result + ((vehicles == null) ? 0 : vehicles.hashCode());
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
		VehicleResponse other = (VehicleResponse) obj;
		if (respCodeDesc == null) {
			if (other.respCodeDesc != null)
				return false;
		} else if (!respCodeDesc.equals(other.respCodeDesc))
			return false;
		if (vehicles == null) {
			if (other.vehicles != null)
				return false;
		} else if (!vehicles.equals(other.vehicles))
			return false;
		return true;
	}
}