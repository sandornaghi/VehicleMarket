package com.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transformedvehicles.TVehicle;

public class ElasticsearchResponse {

	@JsonIgnoreProperties(ignoreUnknown = true)
	private StatusInfo statusInfo;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private Context context;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private List<TVehicle> tVehicleList;

	public StatusInfo getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(StatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<TVehicle> gettVehicleList() {
		return tVehicleList;
	}

	public void settVehicleList(List<TVehicle> tVehicleList) {
		this.tVehicleList = tVehicleList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((statusInfo == null) ? 0 : statusInfo.hashCode());
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
		ElasticsearchResponse other = (ElasticsearchResponse) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (statusInfo == null) {
			if (other.statusInfo != null)
				return false;
		} else if (!statusInfo.equals(other.statusInfo))
			return false;
		if (tVehicleList == null) {
			if (other.tVehicleList != null)
				return false;
		} else if (!tVehicleList.equals(other.tVehicleList))
			return false;
		return true;
	}
}