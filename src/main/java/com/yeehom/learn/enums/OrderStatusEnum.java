package com.yeehom.learn.enums;

import lombok.Getter;

/**
 * Created by yFoo on 16/01/2018.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消")
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 不建议这样写，代码应该尽可能通用、灵活。
     * 应该写成通用方法，使用接口和工具类实现
     * @param code
     * @return
     */
    public static OrderStatusEnum getOrderStatusEnum(Integer code) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum;
            }
        }
        return null;
    }
}
