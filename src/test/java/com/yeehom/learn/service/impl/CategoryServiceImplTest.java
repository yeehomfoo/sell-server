package com.yeehom.learn.service.impl;

import com.yeehom.learn.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yFoo on 13/01/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private  CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {

        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn((Arrays.asList(1,2,3,4)));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    @Transactional
    public void save() throws Exception {

        ProductCategory productCategory = new ProductCategory("男生专享", 11);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}