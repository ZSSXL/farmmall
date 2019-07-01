package com.fmall.vo;

import java.math.BigDecimal;

public class TotalPriceVo {

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;

    @Override
    public String toString() {
        return "TotalPriceVo{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public TotalPriceVo() {
        super();
    }

    public TotalPriceVo(BigDecimal price, Integer quantity, BigDecimal totalPrice) {
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public TotalPriceVo( Integer quantity,BigDecimal price) {
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
