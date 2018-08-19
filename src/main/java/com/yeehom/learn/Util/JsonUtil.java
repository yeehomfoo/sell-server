package com.yeehom.learn.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by yFoo on 29/01/2018.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
