package com.yeehom.learn.Util;

import java.util.Random;

/**
 * Created by yFoo on 27/01/2018.
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
