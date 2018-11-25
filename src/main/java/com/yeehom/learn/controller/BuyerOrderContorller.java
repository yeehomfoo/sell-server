package com.yeehom.learn.controller;

import com.yeehom.learn.util.ResultVOUtil;
import com.yeehom.learn.vo.ResultVO;
import com.yeehom.learn.converter.OrderForm2OrderDTOConverter;
import com.yeehom.learn.dto.OrderDTO;
import com.yeehom.learn.enums.ResultEnum;
import com.yeehom.learn.exception.SellException;
import com.yeehom.learn.form.OrderForm;
import com.yeehom.learn.service.BuyerService;
import com.yeehom.learn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yFoo on 27/01/2018.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderContorller {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        // 转换Date -> Long 低效做法

        return ResultVOUtil.success((orderDTOPage.getContent()));
    }

    // 订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //不安全的做法，需要使用openid
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid))
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        // 不安全，改进
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel(orderDTO);

        buyerService.cancelOrder(openid, orderId);

        return ResultVOUtil.success();
    }

}

