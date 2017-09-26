package com.jrbac.service;

import java.util.List;
import com.jrbac.entity.SearchLog;

public interface SearchLogServer {
    /**
     * 根据id删除
     * 
     * @param slId
     * @return
     */
    public int deleteByPrimaryKey(Integer slId);

    /**
     * 动态添加
     * 
     * @param record
     * @return
     */
    public int insertSelective(SearchLog record);

    /**
     * 根据id查询
     * 
     * @param slId
     * @return
     */
    public SearchLog selectByPrimaryKey(Integer slId);

    /**
     * 根据 搜索记录数降序排列
     * 
     * @param offset
     * @param limit
     * @return
     */
    public List<SearchLog> selectSearchLogAll(int offset, int limit);

    /**
     * 主要用于修改搜索记录条数
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(SearchLog record);

    /**
     * 根据搜索内容查询
     * 
     * @param slContent
     * @return
     */
    public SearchLog selectByContent(String slContent);
}
