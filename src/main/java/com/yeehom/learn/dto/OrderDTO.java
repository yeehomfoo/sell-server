package com.yeehom.learn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yeehom.learn.util.EnumUtil;
import com.yeehom.learn.util.serializer.Date2LongSerializer;
import com.yeehom.learn.dataobject.OrderDetail;
import com.yeehom.learn.enums.OrderStatusEnum;
import com.yeehom.learn.enums.PayStatusEnum;
import lombok.Data;

import java.io.Serializable;
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
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -5802013496823186863L;

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
