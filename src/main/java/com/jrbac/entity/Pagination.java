package com.jrbac.entity;

import java.util.List;
import java.util.Map;

public class Pagination {
    // 当前页
    private Integer             pageNo;
    // 页大小
    private Integer             pageSize;
    // 总数量
    private Integer             numFound;
    // 查询到的结果集合
    private List<Commodity>     commodities;
    // map 装已经选择的条件
    private Map<String, String> map;
    // 品牌结果集
    private List<Brand>         brands;
    // 拼写检查值
    private List<String>        spellcheck;

    public Pagination() {
    }

    public Pagination(Integer pageNo, Integer pageSize, Integer numFound, List<Commodity> commodities) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.numFound = numFound;
        this.commodities = commodities;
    }

    public Pagination(Integer pageNo, Integer pageSize, Integer numFound, List<Commodity> commodities, List<String> spellcheck) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.numFound = numFound;
        this.commodities = commodities;
        this.spellcheck = spellcheck;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public List<String> getSpellcheck() {
        return spellcheck;
    }

    public void setSpellcheck(List<String> spellcheck) {
        this.spellcheck = spellcheck;
    }

    @Override
    public String toString() {
        return "Pagination [pageNo=" + pageNo + ", pageSize=" + pageSize + ", numFound=" + numFound + ", commodities=" + commodities + ", map=" + map + ", brands=" + brands + ", spellcheck=" + spellcheck + "]";
    }
}
