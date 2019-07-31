package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}