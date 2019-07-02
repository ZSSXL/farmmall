package com.fmall.vo;

public class LivestockSimple {

    private Integer label;

    private String varieties;

    public LivestockSimple() {
        super();
    }

    public LivestockSimple(Integer label, String varieties) {
        this.label = label;
        this.varieties = varieties;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }
}
