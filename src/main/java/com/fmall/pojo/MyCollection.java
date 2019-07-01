package com.fmall.pojo;

public class MyCollection {
    private Integer id;

    private Integer userId;

    private Integer productId;

    public MyCollection(Integer id, Integer userId, Integer productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public MyCollection() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}