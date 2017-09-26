package com.jrbac.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jrbac.dao.SearchLogDao;
import com.jrbac.entity.SearchLog;
import com.jrbac.service.SearchLogServer;

@Service
public class SearchLogServerImpl implements SearchLogServer {
    @Autowired
    private SearchLogDao searchLogDao;

    /**
     * 根据id删除
     * 
     * @param slId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer slId) {
        return searchLogDao.deleteByPrimaryKey(slId);
    }

    /**
     * 动态添加
     * 
     * @param record
     * @return
     */
    @Override
    public int insertSelective(SearchLog record) {
        return searchLogDao.insertSelective(record);
    }

    /**
     * 根据id查询
     * 
     * @param slId
     * @return
     */
    @Override
    public SearchLog selectByPrimaryKey(Integer slId) {
        return searchLogDao.selectByPrimaryKey(slId);
    }

    /**
     * 根据 搜索记录数降序排列
     * 
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<SearchLog> selectSearchLogAll(int offset, int limit) {
        return searchLogDao.selectSearchLogAll(offset, limit);
    }

    /**
     * 主要用于修改搜索记录条数
     * 
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(SearchLog record) {
        return searchLogDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据搜索内容查询
     * 
     * @param slContent
     * @return
     */
    @Override
    public SearchLog selectByContent(String slContent) {
        return searchLogDao.selectByContent(slContent);
    }
}
