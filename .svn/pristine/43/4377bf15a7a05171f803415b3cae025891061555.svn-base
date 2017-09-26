package com.jrbac.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jrbac.entity.SearchLog;

public interface SearchLogDao {
    /**
     * 根据id删除
     * 
     * @param slId
     * @return
     */
    int deleteByPrimaryKey(Integer slId);

    /**
     * 动态添加
     * 
     * @param record
     * @return
     */
    int insertSelective(SearchLog record);

    /**
     * 根据id查询
     * 
     * @param slId
     * @return
     */
    SearchLog selectByPrimaryKey(Integer slId);

    /**
     * 根据搜索内容查询
     * 
     * @param slContent
     * @return
     */
    SearchLog selectByContent(String slContent);

    /**
     * 根据 搜索记录数降序排列
     * 
     * @param offset
     * @param limit
     * @return
     */
    List<SearchLog> selectSearchLogAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 主要用于修改搜索记录条数
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SearchLog record);
}