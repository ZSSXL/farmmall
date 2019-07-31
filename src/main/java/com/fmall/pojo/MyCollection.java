package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCollection {
    private Integer id;

    private Integer userId;

    private Integer productId;
}