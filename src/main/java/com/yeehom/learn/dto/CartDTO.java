package com.yeehom.learn.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yFoo on 27/01/2018.
 */
@Data
public class CartDTO implements Serializable {

    private static final long serialVersionUID = 9086563513726648830L;

    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
