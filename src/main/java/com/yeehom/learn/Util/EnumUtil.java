package com.yeehom.learn.util;

import com.yeehom.learn.enums.CodeEnum;

/**
 * Created by yFoo on 13/02/2018.
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (code.equals(enumConstant.getCode())) {
                return enumConstant;
            }
        }
        return null;
    }
}
