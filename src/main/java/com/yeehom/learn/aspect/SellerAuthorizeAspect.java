package com.yeehom.learn.aspect;

import com.yeehom.learn.Util.CookieUtil;
import com.yeehom.learn.constant.CookieConstant;
import com.yeehom.learn.constant.RedisConstant;
import com.yeehom.learn.exception.SellerAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.yeehom.learn.controller.Seller*.*(..))" +
            "&& !execution(public * com.yeehom.learn.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN_NAME);
        if (null == cookie) {
            log.warn("【登录检验】cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】redis中查不到token");
            throw new SellerAuthorizeException();
        }

    }
}
