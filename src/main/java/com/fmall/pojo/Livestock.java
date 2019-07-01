package com.fmall.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Livestock {
    private Integer id;

    private Integer label;

    private String varieties;

    private BigDecimal weight;

    private String faces;

    private String stapleFood;

    private String medicalRecord;

    private String health;

    private String vaccine;

    private Integer age;

    private String photo;

    private String origin;

    private Date createTime;

    private Date updateTime;

    public Livestock(Integer id, Integer label, String varieties, BigDecimal weight, String faces, String stapleFood, String medicalRecord, String health, String vaccine, Integer age, String photo, String origin, Date createTime, Date updateTime) {
        this.id = id;
        this.label = label;
        this.varieties = varieties;
        this.weight = weight;
        this.faces = faces;
        this.stapleFood = stapleFood;
        this.medicalRecord = medicalRecord;
        this.health = health;
        this.vaccine = vaccine;
        this.age = age;
        this.photo = photo;
        this.origin = origin;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Livestock(Integer label, String varieties) {
        this.label = label;
        this.varieties = varieties;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Livestock() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties == null ? null : varieties.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getFaces() {
        return faces;
    }

    public void setFaces(String faces) {
        this.faces = faces == null ? null : faces.trim();
    }

    public String getStapleFood() {
        return stapleFood;
    }

    public void setStapleFood(String stapleFood) {
        this.stapleFood = stapleFood == null ? null : stapleFood.trim();
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord == null ? null : medicalRecord.trim();
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine == null ? null : vaccine.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
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