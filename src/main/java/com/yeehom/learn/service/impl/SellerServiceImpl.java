package com.yeehom.learn.service.impl;

import com.yeehom.learn.dataobject.SellerInfo;
import com.yeehom.learn.repository.SellerInfoRepository;
import com.yeehom.learn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
