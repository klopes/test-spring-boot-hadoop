package com.lopes.data.service.model.beans;

import java.util.List;


public class Data {
    private String email;
    private List<Asset> assets;
    private List<UserActivity> activities;
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public final List<Asset> getAssets() {
		return assets;
	}

	public final void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public final List<UserActivity> getActivities() {
		return activities;
	}

	public final void setActivities(List<UserActivity> activities) {
		this.activities = activities;
	}

}
