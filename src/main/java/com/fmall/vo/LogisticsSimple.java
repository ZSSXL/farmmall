package com.fmall.vo;

import java.math.BigDecimal;

public class LogisticsSimple {

    private BigDecimal longitude;

    private BigDecimal latitude;

    public LogisticsSimple() {
        super();
    }

    public LogisticsSimple(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LogisticsSimple{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
