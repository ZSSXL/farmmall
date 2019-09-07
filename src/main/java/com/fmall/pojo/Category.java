package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ZSS
 * @description 分类实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;
}