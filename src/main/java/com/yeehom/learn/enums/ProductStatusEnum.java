package com.yeehom.learn.enums;

import lombok.Getter;

/**
 * Created by yFoo on 13/01/2018.
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;
    private Integer code;

    private String message;


    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
