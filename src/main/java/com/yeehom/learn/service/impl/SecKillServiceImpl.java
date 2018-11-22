package com.yeehom.learn.service.impl;

import com.yeehom.learn.Util.KeyUtil;
import com.yeehom.learn.exception.SellException;
import com.yeehom.learn.service.SecKillService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yFoo
 * @date 23/11/2018
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456", 1000000);
        stock.put("123456", 100000);
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    /**
     * 解决多线程并发问题：
     * 1、加synchronize。这是一种解决方案，但是无法做细粒度控制，存在单点问题。
     * 2、用基于 Redis 的分布式锁。
     * @param prodcutId
     */
    @Override
    public synchronized void orderProdcutMockDiffUser(String prodcutId) {
        // 1. 查询该商品库存，为0则活动结束
        Integer stockNum = stock.get(prodcutId);
        if (0 == stockNum) {
            throw new SellException(100, "活动已结束");
        }

        // 2. 下单（模拟不同用户openid不同）
        orders.put(KeyUtil.genUniqueKey(), prodcutId);
        // 3. 减库存
        stockNum--;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stock.put(prodcutId, stockNum);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量：" + products.get(productId) +
                "份，还剩：" + stock.get(productId) +
                "份，该商品成功下单用户数目：" + orders.size();
    }
}
