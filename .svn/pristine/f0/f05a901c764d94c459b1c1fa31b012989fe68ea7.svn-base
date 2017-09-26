package com.jrbac.service;

import java.util.List;
import com.jrbac.entity.Commodity;
import com.jrbac.entity.Pagination;

public interface CommodityService {
    /**
     * 查看所有的商品
     * 
     * @param user
     * @return
     */
    public List<Commodity> queryAll();

    /**
     * 删除商品
     * 
     * @param id
     * @return
     */
    public int delete(Integer comId);

    /**
     * 修改商品
     * 
     * @param record
     * @return
     */
    public int update(Commodity record);

    /**
     * 添加商品
     * 
     * @param record
     * @return
     */
    public int insertSelective(Commodity record);

    /**
     * solr高亮查询
     * 
     * @param pageNo
     * @param keyword
     * @param price
     * @param brandId
     * @return
     */
    public Pagination selectPaginationFromSolr(Integer pageNo, String keyword, String price, Integer brandId, String type, String statu);

    /**
     * 自动建议查询词
     * 
     * @param keyword
     * @return
     */
    public List<String> selectSuggestSearchFromSolr(String keyword);
}
