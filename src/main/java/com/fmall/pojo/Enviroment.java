package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enviroment {
    private Integer id;

    private Integer annimalId;

    private Double temperature;

    private Double humidity;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date createTime;

    private Date updateTime;

    private Integer label;
}