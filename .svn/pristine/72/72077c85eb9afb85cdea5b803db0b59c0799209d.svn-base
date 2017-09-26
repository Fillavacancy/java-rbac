package com.jrbac.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jrbac.dao.CommodityDao;
import com.jrbac.entity.Brand;
import com.jrbac.entity.Commodity;
import com.jrbac.entity.Pagination;
import com.jrbac.entity.ProductQuery;
import com.jrbac.service.CommodityService;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;
    /*
     * @Resource(name = "solrServer") private CloudSolrServer solrServer;
     */
    @Autowired
    private SolrServer   solrServer;

    @Override
    public List<Commodity> queryAll() {
        return commodityDao.selectAll();
    }

    @Override
    public int delete(Integer comId) {
        return commodityDao.deleteByPrimaryKey(comId);
    }

    @Override
    public int update(Commodity record) {
        return commodityDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(Commodity record) {
        return commodityDao.insertSelective(record);
    }

    /**
     * solr高亮查询
     * 
     * @param pageNo
     * @param keyword
     * @param price
     * @param brandId
     * @return
     */
    @Override
    public Pagination selectPaginationFromSolr(Integer pageNo, String keyword, String price, Integer brandId, String type, String statu) {
        ProductQuery productQuery = new ProductQuery();
        // 当前页
        productQuery.setPageNo(pageNo);
        // 开始行
        productQuery.setStartRow(0);
        // 每页数
        productQuery.setPageSize(8);
        SolrQuery solrQuery = new SolrQuery();
        // 关键词 商品名称
        solrQuery.set("q", "com_all:" + keyword);
        // solrQuery.set("qt", "/suggest");// 请求到suggest中
        // 回显数据
        StringBuilder params = new StringBuilder();
        params.append("keyword=").append(keyword);
        // 排序
        if (!"".equals(type) && null != statu && !"".equals(statu)) {
            String[] t = type.split(",");
            if (t.length >= 1) {
                for (int i = 0; i < t.length; i++) {
                    solrQuery.addSortField(t[i], "desc".equals(statu) ? ORDER.desc : ORDER.asc);
                }
            }
        }
        // 高亮
        // 1,设置, 打开高亮的开关
        solrQuery.setHighlight(true);
        // 2, 设置高亮字段
        solrQuery.addHighlightField("comName,comTitle");
        // 3, 设置关键字高亮的样式 <span style='color:red'>2016</span>
        // 设置前缀和后缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        // 过滤条件 品牌
        if (null != brandId) {
            solrQuery.addFilterQuery("comBrand:" + brandId);
            params.append("&brandId=").append(brandId);
        }
        // 过滤价格 0-99 1600
        if (null != price && !price.equals("")) {
            String[] split = price.split("-");
            // 如果切割后的长度等于2 就说明这是一个价格区间
            if (split.length == 2) {
                solrQuery.addFilterQuery("comPrice:[" + split[0] + " TO " + split[1] + "]");
            }
            else {
                solrQuery.addFilterQuery("comPrice:[" + split[0] + " TO *]");
            }
            params.append("&price=").append(price);
        }
        // 分页 limit 开始行,每页数
        solrQuery.setStart(productQuery.getStartRow());
        solrQuery.setRows(productQuery.getPageSize());
        // 拼写检查建议
        // solrQuery.set("spellcheck", "true");
        // solrQuery.set("spellcheck.q", keyword);
        // solrQuery.set("spellcheck.count", 5);
        QueryResponse response = null;
        try {
            response = solrServer.query(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> wordList = new ArrayList<String>();
        // 当搜索不到结果时，显示建议词
        SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
        if (spellCheckResponse != null) {
            if (!spellCheckResponse.isCorrectlySpelled()) {
                for (Suggestion s : spellCheckResponse.getSuggestions()) {
                    wordList.addAll(s.getAlternatives());
                }
            }
        }
        // 分析这个Map
        // 第一层Map: Key String == ID : Value: Map
        // 第二层Map: Key String == name_ik : Value: List
        // 获取到List: String 0,1,2....
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<Commodity> commodities = new ArrayList<Commodity>();
        // 结果集
        SolrDocumentList docs = response.getResults();
        // 总条数
        long numFound = docs.getNumFound();
        Pagination pagination = new Pagination();
        if (numFound > 0) {
            for (SolrDocument doc : docs) {
                Commodity commodity = new Commodity();
                // 商品的ID
                String id = (String) doc.get("id");
                commodity.setComId(Integer.parseInt(id));
                // 取第二层Map
                Map<String, List<String>> map = highlighting.get(id);
                // 取 高亮 comName List集合
                List<String> list = map.get("comName");
                if (null != list && list.size() > 0)
                    commodity.setComName(list.get(0)); // list.get(0) 中的name是已经设置为高亮的
                else
                    commodity.setComName(doc.getFieldValue("comName").toString());
                // 取 高亮 comTitle List集合
                list = map.get("comTitle");
                // 标题
                if (null != list && list.size() > 0)
                    commodity.setComTitle(list.get(0));
                else
                    commodity.setComTitle(doc.getFieldValue("comTitle").toString());
                // 内容
                commodity.setComContent(doc.get("comContent").toString());
                // 价格 这里的价格本身是保存在bbs_sku表中的, 而我们在这里将price属性直接添加到了Product中
                // 因为我们在做上架的时候, 查询的是bbs_sku中price最小的值 然后保存到solr中的, 所以这里我们就直接将price属性添加到product中了
                // 这里的价格只有一个值
                // Float price = (Float)doc.get("price");
                // 价格
                commodity.setComPrice((Double) doc.get("comPrice"));
                // 数量
                commodity.setComNumber(Integer.parseInt(doc.get("comNumber").toString()));
                // 销量
                commodity.setComSalesvolume(Integer.parseInt(doc.get("comSalesvolume").toString()));
                // 品牌ID
                commodity.setComBrand(new Brand((Integer) doc.get("comBrand")));
                // 图片
                commodity.setComImgurl(doc.get("comImgurl").toString());
                System.out.println(doc.get("suggestion"));
                System.out.println(doc.get("comText"));
                commodities.add(commodity);
            }
            pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), (int) numFound, commodities);
            // // 页面展示
            // String url = "/search";
            // pagination.pageView(url, params.toString());
        }
        else {
            // 拼写检查提醒
            if (null != wordList && wordList.size() > 0) {
                pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), (int) numFound, commodities, wordList);
            }
        }
        return pagination;
    }

    /**
     * 自动建议查询词
     * 
     * @param keyword
     * @return
     */
    @Override
    public List<String> selectSuggestSearchFromSolr(String keyword) {
        List<String> suggests = new ArrayList<String>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("qt", "/suggest");
        solrQuery.set("q", "com_all:" + keyword);
        QueryResponse response = null;
        try {
            response = solrServer.query(solrQuery);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        SpellCheckResponse suggest = response.getSpellCheckResponse();
        if (suggest != null) {
            if (!suggest.isCorrectlySpelled()) {
                for (Suggestion s : suggest.getSuggestions()) {
                    // System.out.println("Suggestions NumFound: " + s.getNumFound());
                    // System.out.println("Token: " + s.getToken());
                    suggests.addAll(s.getAlternatives());// Suggested
                }
            }
        }
        return suggests;
    }

    /**
     * 定时器
     * 
     * @param val
     */
    public void autoSmsTime(String val) {
        System.out.println("--------clean-----" + val);
    }
}
