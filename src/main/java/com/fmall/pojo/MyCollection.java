package com.fmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZSS
 * @description 个人收藏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCollection {
    private Integer id;

    private Integer userId;

    private Integer productId;
}