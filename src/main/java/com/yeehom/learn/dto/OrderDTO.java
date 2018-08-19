package com.yeehom.learn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yeehom.learn.Util.EnumUtil;
import com.yeehom.learn.Util.serializer.Date2LongSerializer;
import com.yeehom.learn.dataobject.OrderDetail;
import com.yeehom.learn.enums.OrderStatusEnum;
import com.yeehom.learn.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yFoo on 17/01/2018.
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
//        OrderStatusEnum.getOrderStatusEnum(this.orderStatus);
        return EnumUtil.getByCode(this.orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(this.payStatus, PayStatusEnum.class);
    }
}
