package com.fmall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Logistics {
    private Integer id;

    private Integer boxId;

    private Double temperature;

    private Double humidity;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date createTime;

    private Date updateTime;

    public Logistics(Integer id, Integer boxId, Double temperature, Double humidity, BigDecimal longitude, BigDecimal latitude, Date createTime, Date updateTime) {
        this.id = id;
        this.boxId = boxId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Logistics() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}