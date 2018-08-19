package com.yeehom.learn.service;

import com.yeehom.learn.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by yFoo on 13/01/2018.
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
