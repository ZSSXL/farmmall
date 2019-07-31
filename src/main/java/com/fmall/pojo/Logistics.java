package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logistics {
    private Integer id;

    private Integer boxId;

    private Double temperature;

    private Double humidity;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date createTime;

    private Date updateTime;

}