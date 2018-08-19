package com.yeehom.learn.enums;

import lombok.Getter;

/**
 * Created by yFoo on 16/01/2018.
 */
@Getter
public enum PayStatusEnum implements CodeEnum  {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}