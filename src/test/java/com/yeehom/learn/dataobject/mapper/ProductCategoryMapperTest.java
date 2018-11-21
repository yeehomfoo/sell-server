package com.yeehom.learn.dataobject.mapper;

import com.yeehom.learn.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yFoo
 * @date ${date}
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "我的最爱");
        map.put("category_type", 102);
        int count = mapper.insertByMap(map);

        Assert.assertEquals(1, count);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("我的最爱");
        productCategory.setCategoryType(103);
        int count = mapper.insertByObject(productCategory);

        Assert.assertEquals(1, count);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(103);

        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> result = mapper.findByCategoryName("我的最爱");

        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryType", 103);
        map.put("categoryName", "我的最不爱");
        int count = mapper.updateByMap(map);

        Assert.assertEquals(1, count);
    }

    @Test
    public void updateByCategoryType() {
        int count = mapper.updateByCategoryType(103, "我的最爱");

        Assert.assertEquals(1, count);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(103);
        productCategory.setCategoryName("我的最不爱");
        int count = mapper.updateByObject(productCategory);

        Assert.assertEquals(1, count);
    }

    @Test
    public void deleteByCategoryType() {
        int count = mapper.deleteByCategoryType(103);

        Assert.assertEquals(1, count);
    }

    @Test
    public void selectByProductCategoryType() {
        ProductCategory result = mapper.selectByProductCategoryType(101);

        Assert.assertNotNull(result);
    }
}