package com.yeehom.learn.form;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryForm {

    private Integer categoryId; // 类目ID

    private String categoryName; // 类目名字

    private Integer categoryType;  // 类目编号

    private Date createTime;

    private Date updateTime;
}
