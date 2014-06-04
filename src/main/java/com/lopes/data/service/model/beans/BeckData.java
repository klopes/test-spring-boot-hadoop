package com.lopes.data.service.model.beans;

import java.util.List;


public class BeckData {
    private String email;
    private List<RecommendedAsset> recommendedAssets;
    private List<UserActivity> activities;
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public final List<RecommendedAsset> getRecommendedAssets() {
		return recommendedAssets;
	}

	public final void setRecommendedAssets(List<RecommendedAsset> recommendedAssets) {
		this.recommendedAssets = recommendedAssets;
	}

	public final List<UserActivity> getActivities() {
		return activities;
	}

	public final void setActivities(List<UserActivity> activities) {
		this.activities = activities;
	}

}
