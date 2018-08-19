package com.yeehom.learn.service;

import com.yeehom.learn.dto.OrderDTO;

/**
 * Created by yFoo on 27/01/2018.
 */
public interface BuyerService {

    // 查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    // 取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
