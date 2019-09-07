package com.fmall.pojo;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;
}