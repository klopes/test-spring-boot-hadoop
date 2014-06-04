package com.lopes.data.service.model.beans;


public class RecommendedAsset {
    private float haversineDistance;
    private long globalPropertyId;
    private float propertyLatitude;
    private float propertyLongitude;
    private String propertyState;
    private String productType;
    private String bidStartDt;
    private String bidEndDt;
    private int startingBid;
    private String propertyCity;
    private String pdp_url;
    private String thumbnail_url;

    public float getHaversineDistance() {
        return haversineDistance;
    }

    public void setHaversineDistance(float haversineDistance) {
        this.haversineDistance = haversineDistance;
    }

    public long getGlobalPropertyId() {
        return globalPropertyId;
    }

    public void setGlobalPropertyId(long globalPropertyId) {
        this.globalPropertyId = globalPropertyId;
    }

    public float getPropertyLatitude() {
        return propertyLatitude;
    }

    public void setPropertyLatitude(float propertyLatitude) {
        this.propertyLatitude = propertyLatitude;
    }

    public float getPropertyLongitude() {
        return propertyLongitude;
    }

    public void setPropertyLongitude(float propertyLongitude) {
        this.propertyLongitude = propertyLongitude;
    }

    public String getPropertyState() {
        return propertyState;
    }

    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBidStartDt() {
        return bidStartDt;
    }

    public void setBidStartDt(String bidStartDt) {
        this.bidStartDt = bidStartDt;
    }

    public String getBidEndDt() {
        return bidEndDt;
    }

    public void setBidEndDt(String bidEndDt) {
        this.bidEndDt = bidEndDt;
    }

    public float getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(int startingBid) {
        this.startingBid = startingBid;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPdp_url() {
        return pdp_url;
    }

    public void setPdp_url(String pdp_url) {
        this.pdp_url = pdp_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
}
