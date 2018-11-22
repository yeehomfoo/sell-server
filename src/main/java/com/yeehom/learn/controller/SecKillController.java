package com.yeehom.learn.controller;

import com.yeehom.learn.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yFoo
 * @date 23/11/2018
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable("productId") String productId) {
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到获得"哎呦喂，XXXXX"，抢到了会返回剩余的库存量
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public  String order(@PathVariable("productId") String productId) {
        log.info("@skill request, productId:" + productId);
        secKillService.orderProdcutMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
}
