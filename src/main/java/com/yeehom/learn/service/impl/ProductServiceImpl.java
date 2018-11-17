package com.yeehom.learn.service.impl;

import com.yeehom.learn.dataobject.ProductInfo;
import com.yeehom.learn.dto.CartDTO;
import com.yeehom.learn.enums.ProductStatusEnum;
import com.yeehom.learn.enums.ResultEnum;
import com.yeehom.learn.exception.SellException;
import com.yeehom.learn.repository.ProductInfoRepository;
import com.yeehom.learn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yFoo on 13/01/2018.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if (null == productInfo) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (ProductStatusEnum.UP.getCode().equals(productInfo.getProductStatus())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新状态
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoRepository.save(productInfo);
    }

    @Override
    public void offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if (null == productInfo) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatusEnum.DOWN.equals(productInfo.getProductStatus())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新状态
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoRepository.save(productInfo);
    }
}
