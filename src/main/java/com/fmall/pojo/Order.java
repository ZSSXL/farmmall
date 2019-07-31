package com.fmall.pojo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer boxId;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;
}