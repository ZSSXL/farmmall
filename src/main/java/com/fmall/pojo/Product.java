package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZSS
 * @description 商品实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer id;

    private Integer userId;

    private Integer categoryId;

    private Integer livestock;

    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}