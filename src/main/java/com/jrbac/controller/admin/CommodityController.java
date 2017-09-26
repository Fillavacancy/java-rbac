package com.jrbac.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jrbac.context.BaseReturn;
import com.jrbac.context.ErrorCode;
import com.jrbac.context.Param;
import com.jrbac.entity.Brand;
import com.jrbac.entity.Commodity;
import com.jrbac.entity.Pagination;
import com.jrbac.entity.SearchLog;
import com.jrbac.service.BrandService;
import com.jrbac.service.CommodityService;
import com.jrbac.service.SearchLogServer;
import com.jrbac.util.ValidateUtils;
import com.jrbac.util.DateFormat;

@Controller
@RequestMapping(value = "/admin/commodity")
public class CommodityController {
    private final Logger     logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommodityService commodityService;
   /* @Resource(name = "solrServer")
    private CloudSolrServer       solrServer;*/
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private BrandService     brandService;
    @Autowired
    private SearchLogServer  searchLogServer;

    /**
     * 商品查询
     * 
     * @param model
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/searcher.html")
    public String setting(Model model) {
        String title = "商品查询";
        model.addAttribute("title", title);
        return "/admin/commodity/searcher";
    }

    /**
     * 商品管理 查询所有商品列表
     * 
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/index.html")
    public String index(Model model, HttpSession session) {
        String title = "商品管理";
        List<Commodity> commodities = commodityService.queryAll();
        List<Brand> brands = brandService.selectBrandList();
        if (ValidateUtils.isNotNullArray(commodities)) {
            model.addAttribute(Param.COM_LIST, commodities);
        }
        if (ValidateUtils.isNotNullArray(brands)) {
            model.addAttribute(Param.BRAND_LIST, brands);
        }
        model.addAttribute("title", title);
        return "/admin/commodity/index";
    }

    /**
     * 添加商品
     * 
     * @param commodity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Commodity commodity) {
        logger.info("-----正在添加商品,传过来的数据：{}-----", commodity.toString());
        int count = 0;
        try {
            if (commodity != null) {
                SolrInputDocument doc = new SolrInputDocument();
                if (commodity.getComId() != null) {
                    // 修改
                    if (StringUtils.isBlank(commodity.getComId().toString())) {
                        commodity.setComUpdate(DateFormat.getSimpleDateFormat());
                        return BaseReturn.response(ErrorCode.PARAM_ERROR, "com_Id不能为空");
                    }
                    count = commodityService.update(commodity);
                }
                else {
                    // 添加
                    commodity.setComCreatedate(DateFormat.getSimpleDateFormat());
                    commodity.setComUpdate(DateFormat.getSimpleDateFormat());
                    count = commodityService.insertSelective(commodity);
                }
                // 保存商品信息到Solr服务器
                if (commodity.getComState() == 1) {
                    doc.addField("id", commodity.getComId());
                    doc.addField("comName", commodity.getComName());
                    doc.addField("comTitle", commodity.getComTitle());
                    doc.addField("comContent", commodity.getComContent());
                    doc.addField("comPrice", commodity.getComPrice());
                    doc.addField("comNumber", commodity.getComNumber());
                    doc.addField("comType", commodity.getComType());
                    doc.addField("comSalesvolume", commodity.getComSalesvolume() == null ? 0 : commodity.getComSalesvolume());
                    doc.addField("comBrand", commodity.getComBrand().getBrId());
                    doc.addField("comImgurl", commodity.getComImgurl());
                    solrServer.add(doc);
                }
                else {
                    solrServer.deleteById(commodity.getComId().toString());
                }
                solrServer.commit();
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("----------添加数据：{}条----------", count);
        if (count > 0) {
            return BaseReturn.response(ErrorCode.SUCCESS);
        }
        else {
            return BaseReturn.response(ErrorCode.FAILURE);
        }
    }

    /**
     * 删除商品
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        logger.debug("-----删除商品-----");
        logger.info("-----传过来的id:{}-----", id);
        if (StringUtils.isBlank(id.toString())) {
            return BaseReturn.response(ErrorCode.PARAM_ERROR, "com_Id不能为空");
        }
        else {
            int deleteCount = 0;
            deleteCount = commodityService.delete(id);
            try {
                solrServer.deleteById(id.toString());
                solrServer.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (deleteCount > 0) {
                return BaseReturn.response(ErrorCode.SUCCESS, deleteCount);
            }
            else {
                return BaseReturn.response(ErrorCode.FAILURE, "删除数据失败");
            }
        }
    }

    /**
     * 重构商品
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reconstructed", method = RequestMethod.POST)
    public String reconstructed() {
        List<Commodity> commodities = commodityService.queryAll();
        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        SolrInputDocument doc = null;
        for (Commodity commodity : commodities) {
            if (commodity.getComState() == 1) {
                doc = new SolrInputDocument();
                doc.addField("id", commodity.getComId());
                doc.addField("comName", commodity.getComName());
                doc.addField("comTitle", commodity.getComTitle());
                doc.addField("comContent", commodity.getComContent());
                doc.addField("comPrice", commodity.getComPrice());
                doc.addField("comNumber", commodity.getComNumber());
                doc.addField("comType", commodity.getComType());
                doc.addField("comSalesvolume", commodity.getComSalesvolume());
                doc.addField("comBrand", commodity.getComBrand().getBrId());
                doc.addField("comImgurl", commodity.getComImgurl());
                docs.add(doc);
            }
        }
        try {
            solrServer.add(docs);
            solrServer.commit();
            return BaseReturn.response(ErrorCode.SUCCESS, "重构商品成功");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseReturn.response(ErrorCode.FAILURE, "重构商品失败");
    }

    @ResponseBody
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public void test2() {
        List<Commodity> commodities = commodityService.queryAll();
        List<String> docs = new ArrayList<String>();
        for (Commodity commodity : commodities) {
            if (commodity.getComState() != 1) {
                docs.add(commodity.getComId().toString());
                System.out.println(commodity.getComId());
            }
        }
        try {
            solrServer.deleteById(docs);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空商品
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/empty", method = RequestMethod.POST)
    public String empty() {
        try {
            solrServer.deleteByQuery("*:*");
            solrServer.commit();
            return BaseReturn.response(ErrorCode.SUCCESS, "清空商品成功");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseReturn.response(ErrorCode.FAILURE, "清空商品失败");
    }

    /**
     * 基于高亮搜索 附带多条件过滤
     * 
     * @param pageNo
     * @param keyword
     * @param price
     * @param brandId
     * @param statu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Integer pageNo, String keyword, String price, Integer brandId, String statu) {
        // 品牌结果集
        List<Brand> brands = brandService.selectBrandList();
        // map 装已经选择的条件
        Map<String, String> map = new HashMap<String, String>();
        if (null != brandId) {
            for (Brand brand : brands) {
                if (brandId.equals(brand.getBrId())) {
                    map.put("品牌", brand.getBrName());
                    break;
                }
            }
        }
        // 价格 0-99 1600以上
        if (null != price) {
            String[] split = price.split("-");
            // 如果切割后的长度等于2 就说明这是一个价格区间
            if (split.length == 2) {
                map.put("价格", price);
            }
            else {
                map.put("价格", price + "以上");
            }
        }
        // 排序条件
        String type = "";
        if (null != statu && !"".equals(statu)) {
            String[] val = statu.split(":");
            // 综合
            if ("com".equals(val[0])) {
                type = "comSalesvolume,comPrice";
            }
            // 销量
            if ("sales".equals(val[0])) {
                type = "comSalesvolume";
            }
            // 价格
            if ("price".equals(val[0])) {
                type = "comPrice";
            }
            statu = val[1];
        }
        Pagination pagination = commodityService.selectPaginationFromSolr(pageNo, keyword, price, brandId, type, statu);
        pagination.setBrands(brands);
        pagination.setMap(map);
        // 将用户搜索记录保存下来
        if (ValidateUtils.isNotNullArray(pagination.getCommodities())) {
            SearchLog searchLog = searchLogServer.selectByContent(keyword);
            if (searchLog != null) {
                searchLog.setSlSearchCount(searchLog.getSlSearchCount() + 1);
                searchLogServer.updateByPrimaryKeySelective(searchLog);
            }
            else {
                searchLogServer.insertSelective(new SearchLog(0, keyword, 1));
            }
        }
        return BaseReturn.response(ErrorCode.SUCCESS, pagination);
    }

    /**
     * 搜索自动建议查询词
     * 
     * @param keyword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/suggestSearch", method = RequestMethod.POST)
    public String suggestSearch(String keyword) {
        List<String> suggests = commodityService.selectSuggestSearchFromSolr(keyword);
        return BaseReturn.response(ErrorCode.SUCCESS, suggests);
    }

    /**
     * 检索用户搜索关键字记录
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchLog", method = RequestMethod.POST)
    public String searchLog() {
        List<SearchLog> searchLogs = searchLogServer.selectSearchLogAll(0, 10);
        return BaseReturn.response(ErrorCode.SUCCESS, searchLogs);
    }
}
