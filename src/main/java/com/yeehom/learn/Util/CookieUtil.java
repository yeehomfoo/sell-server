package com.yeehom.learn.Util;

import com.yeehom.learn.constant.CookieConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    /**
     * 设置
     * @param httpServletResponse
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse httpServletResponse,
                           String name,
                           String value,
                           Integer maxAge) {

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 获取 Cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                             String name) {
        Map<String, Cookie> map = readCookies(request);
        return map.get(name);
    }

    /**
     * 将 Cookie 数据转换成 Map
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();

        if (null != cookies) {
            for (Cookie cookie: cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

}
