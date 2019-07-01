package com.fmall.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItemVo {

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;  // 收货地址

    private Integer productId; //

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice; // 单价

    private Integer quantity;   // 数量

    private BigDecimal totalPrice; // 总价

    private Integer paymentType; // 支付类型

    private Integer status; // 订单状态

    public OrderItemVo() {
        super();
    }

    public OrderItemVo(Long orderNo, Integer userId, Integer shippingId, Integer productId, String productName, String productImage, BigDecimal currentUnitPrice, Integer quantity, BigDecimal totalPrice, Integer paymentType, Integer status) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.currentUnitPrice = currentUnitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderItemVo{" +
                "orderNo=" + orderNo +
                ", userId=" + userId +
                ", shippingId=" + shippingId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", currentUnitPrice=" + currentUnitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", paymentType=" + paymentType +
                ", status=" + status +
                '}';
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
