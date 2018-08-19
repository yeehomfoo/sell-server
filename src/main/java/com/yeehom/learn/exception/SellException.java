package com.yeehom.learn.exception;

import com.yeehom.learn.enums.ResultEnum;

/**
 * Created by yFoo on 27/01/2018.
 */
public class SellException extends  RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
