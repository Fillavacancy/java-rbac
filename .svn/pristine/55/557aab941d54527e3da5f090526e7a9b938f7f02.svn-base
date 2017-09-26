package com.jrbac.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jrbac.entity.Brand;

public interface BrandDao {
    /**
     * 查询所有品牌
     * 
     * @return
     */
    List<Brand> selectBrandList();

    /**
     * 根据品牌id查询
     * 
     * @param brId
     * @return
     */
    Brand selectById(@Param("brId") Integer brId);
}