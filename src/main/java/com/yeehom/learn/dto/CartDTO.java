package com.yeehom.learn.dto;

import lombok.Data;

/**
 * Created by yFoo on 27/01/2018.
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
