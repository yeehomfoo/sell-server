package com.yeehom.learn.controller;

import com.lly835.bestpay.model.PayResponse;
import com.yeehom.learn.dto.OrderDTO;
import com.yeehom.learn.enums.ResultEnum;
import com.yeehom.learn.exception.SellException;
import com.yeehom.learn.service.OrderService;
import com.yeehom.learn.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by yFoo on 29/01/2018.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        // 1. 查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 发起支付
        // PayResponse payResponse =   payService.create(orderDTO);
        PayResponse payResponse = this.getPayResponse();


        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        // 发起支付，支付逻辑写到service中；
        return new ModelAndView("pay/create", map);
    }

    private PayResponse getPayResponse() {
        PayResponse payResponse = new PayResponse();
        payResponse.setAppId("appId");
        payResponse.setTimeStamp("timeStamp");
        payResponse.setNonceStr("nonceStr");
        payResponse.setPackAge("packAge");
        payResponse.setPaySign("paySign");

        return payResponse;
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        // 返回给微信处理结果
        return  new ModelAndView("pay/success");
    }
}
