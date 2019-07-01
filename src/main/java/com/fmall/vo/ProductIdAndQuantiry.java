package com.fmall.vo;

public class ProductIdAndQuantiry {

    private Integer productId;

    private Integer quantity;

    public ProductIdAndQuantiry(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductIdAndQuantiry() {
        super();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
