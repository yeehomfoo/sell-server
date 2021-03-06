package com.yeehom.learn.dataobject.mapper;

import com.yeehom.learn.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author yFoo
 * @date ${date}
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
        @Result(column = "category_id", property = "categoryId"),
        @Result(column = "category_name", property = "categoryName"),
        @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByMap(Map<String, Object> map);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryType") Integer categoryType,  @Param("categoryName") String categoryName);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_Type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    /**
     * 通过类目编号查询产品类目
     * 基于 XML 文件的 Mybatis 使用方式
     * 官方并不推崇这种使用方式
     * @param categoryType
     * @return
     */
    ProductCategory selectByProductCategoryType(Integer categoryType);
}
