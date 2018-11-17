package com.yeehom.learn.service.impl;

import com.yeehom.learn.dataobject.ProductInfo;
import com.yeehom.learn.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yFoo on 13/01/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = generateObject("1234567");

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);

    }

    private ProductInfo generateObject(String id) {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(id);
        productInfo.setProductName("皮蛋粥" + id);
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥" + id);
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        return  productInfo;
    }

    @Test
    public void onSale() {
        productService.onSale("123457");
        ProductInfo result = productService.findOne("123457");

        Assert.assertEquals(ProductStatusEnum.UP.getCode(), result.getProductStatus());
    }

    @Test
    public void offSale() {
        productService.offSale("123457");
        ProductInfo result = productService.findOne("123457");

        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), result.getProductStatus());
    }
}