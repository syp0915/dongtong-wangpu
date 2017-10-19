package com.dongtong.basic.dto;

import java.io.Serializable;
import java.util.List;

public class MetroLineInfoDTO  implements Serializable{

	private String lineName;
	private String lineId;
	private List<MetroStationInfoDTO> stationList;
	
	public List<MetroStationInfoDTO> getStationList() {
		return stationList;
	}
	public void setStationList(List<MetroStationInfoDTO> stationList) {
		this.stationList = stationList;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
}
