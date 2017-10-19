package com.dongtong.basic.dto;

import java.io.Serializable;

public class MetroStationInfoDTO implements Serializable{

	private String stationName;
	private String stationId;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
}
