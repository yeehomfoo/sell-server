package com.yeehom.learn.service;

import com.yeehom.learn.dataobject.SellerInfo;

public interface SellerService {

    /**
     * 通过 openid 查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
