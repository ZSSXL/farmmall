package com.fmall.pojo;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Category {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

}