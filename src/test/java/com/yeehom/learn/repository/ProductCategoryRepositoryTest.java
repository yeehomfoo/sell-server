package com.yeehom.learn.repository;

import com.alibaba.fastjson.JSON;
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

/**
 * Created by yFoo on 13/01/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(JSON.toJSONString(productCategory));
    }

    @Test
    @Transactional
    public void saveTest() {
//        ProductCategory productCategory = repository.findOne(2);
//        productCategory.setCategoryId(2);
//        productCategory.setCategoryName("男生最爱的");
//        productCategory.setCategoryType(3);
        ProductCategory productCategory = new ProductCategory("girl's fav", 24);
        ProductCategory result = productCategoryRepository.save(productCategory);
//        Assert.assertNotEquals(null, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2,3,4);

        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

}