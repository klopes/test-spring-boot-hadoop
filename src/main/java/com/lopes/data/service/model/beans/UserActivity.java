package com.lopes.data.service.model.beans;

public class UserActivity {

	private String source;
	private String stateCode;
	private float lat;
	private float lng;

	public final String getSource() {
		return source;
	}

	public final void setSource(String source) {
		this.source = source;
	}

	public final String getStateCode() {
		return stateCode;
	}

	public final void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public final float getLatitude() {
		return lat;
	}

	public final void setLatitude(float lat) {
		this.lat = lat;
	}

	public final float getLongitude() {
		return lng;
	}

	public final void setLongitude(float lng) {
		this.lng = lng;
	}

}
