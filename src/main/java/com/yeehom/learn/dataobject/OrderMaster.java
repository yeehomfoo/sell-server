package com.yeehom.learn.dataobject;

import com.yeehom.learn.enums.OrderStatusEnum;
import com.yeehom.learn.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yFoo on 16/01/2018.
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    // 这种方式不太好，这个类最好只对应数据库字段，一般我们使用dto在mvc各层传输数据
//    @Transient
//    private List<OrderDetail> orderDetailList;

}
