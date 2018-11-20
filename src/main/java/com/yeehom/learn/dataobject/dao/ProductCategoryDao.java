package com.yeehom.learn.dataobject.dao;

import com.yeehom.learn.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 商品类目 Dao
 * mapper 一般不直接使用，由于分层思想，
 * 一般会封装一层 Dao 层，通过 Dao 调用 mapper
 * @author yFoo
 * @date ${date}
 */
@Component
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String, Object> map) {
        return productCategoryMapper.insertByMap(map);
    }
}
