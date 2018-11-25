package com.yeehom.learn.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yFoo on 16/01/2018.
 */
@Entity
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -3770139210893251624L;

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

}
