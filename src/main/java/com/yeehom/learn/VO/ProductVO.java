package com.yeehom.learn.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含类目）
 *
 * Created by yFoo on 14/01/2018.
 */

@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 2636137028073595041L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

 }
