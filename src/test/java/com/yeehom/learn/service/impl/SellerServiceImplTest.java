package com.yeehom.learn.service.impl;

import com.yeehom.learn.dataobject.SellerInfo;
import com.yeehom.learn.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    private static final String OPENID = "123";

    @Autowired
    SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals(OPENID, result.getOpenid());
    }
}