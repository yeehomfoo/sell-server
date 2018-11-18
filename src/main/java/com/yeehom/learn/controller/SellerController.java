package com.yeehom.learn.controller;

import com.yeehom.learn.Util.CookieUtil;
import com.yeehom.learn.constant.CookieConstant;
import com.yeehom.learn.constant.RedisConstant;
import com.yeehom.learn.dataobject.SellerInfo;
import com.yeehom.learn.enums.ResultEnum;
import com.yeehom.learn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String, Object> map,
                              HttpServletResponse httpServletResponse) {
        // 1. openid 与数据库记录做匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo) {
            map.put("msg", ResultEnum.LOGIN_FAILD.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        // 2. 设置 token 至 redis
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);
        // 3. 设置 token 至 cookie
        CookieUtil.set(httpServletResponse, CookieConstant.TOKEN_NAME, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        // 1. 从 cookie 里面查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN_NAME);

        if (null != cookie) {
            // 2. 清除 redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            // 3. 清除 cookie
            CookieUtil.set(response, CookieConstant.TOKEN_NAME, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
