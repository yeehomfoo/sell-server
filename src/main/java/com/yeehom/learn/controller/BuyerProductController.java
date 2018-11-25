package com.yeehom.learn.controller;

import com.yeehom.learn.util.ResultVOUtil;
import com.yeehom.learn.vo.ProductInfoVO;
import com.yeehom.learn.vo.ProductVO;
import com.yeehom.learn.vo.ResultVO;
import com.yeehom.learn.dataobject.ProductCategory;
import com.yeehom.learn.dataobject.ProductInfo;
import com.yeehom.learn.service.CategoryService;
import com.yeehom.learn.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by yFoo on 14/01/2018.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",
            key = "#sellerId",
            condition = "#sellerId.length() > 3",
            unless = "#result.getData().size() == 0")
    public ResultVO<List<ProductVO>> list(String sellerId) {

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList
                .stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService
                .findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = productCategoryList.stream().map(productCategory -> {
           ProductVO productVO = new ProductVO();
           productVO.setCategoryType(productCategory.getCategoryType());
           productVO.setCategoryName(productCategory.getCategoryName());
           productVO.setProductInfoVOList(
                   productInfoList
                           .stream()
                           .filter(productInfo -> productInfo
                                   .getCategoryType()
                                   .equals(productCategory
                                           .getCategoryType()))
                           .map(productInfo -> {
                               ProductInfoVO productInfoVO = new ProductInfoVO();
                               BeanUtils.copyProperties(productInfo, productInfoVO);
                               return  productInfoVO;
                           }).collect(Collectors.toList()));

           return productVO;
        }).collect(Collectors.toList());

//        ProductVO productVO = new ProductVO();
//        ProductInfoVO productInfoVO = new ProductInfoVO();

//        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
//
//        resultVO.setData(Arrays.asList(productVO));
//        ResultVO resultVO = new ResultVO();
//        resultVO.setData(productVOList);
//        resultVO.setCode(ResultEnum.SUCC.getCode());
//        resultVO.setMsg(ResultEnum.SUCC.getMsg());

        return ResultVOUtil.success(productVOList);
    }
}
