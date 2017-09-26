package com.jrbac.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jrbac.entity.Commodity;

public interface CommodityDao {
    /**
     * 根据商品编号删除
     * 
     * @param comId
     * @return
     */
    int deleteByPrimaryKey(@Param("comId") Integer comId);

    /**
     * 添加商品
     * 
     * @param record
     * @return
     */
    int insertSelective(Commodity record);

    /**
     * 根据商品编号查询
     * 
     * @param comId
     * @return
     */
    Commodity selectByPrimaryKey(@Param("comId") Integer comId);

    /**
     * 修改商品
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Commodity record);

    /**
     * 查询所有商品
     * 
     * @return
     */
    List<Commodity> selectAll();
}
