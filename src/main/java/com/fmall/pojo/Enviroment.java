package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author ZSS
 * @description 环境实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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