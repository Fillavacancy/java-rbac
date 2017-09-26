package com.jrbac.entity;

public class SearchLog {
    private Integer slId;
    private String  slContent;
    private Integer slSearchCount;

    public SearchLog() {
        super();
    }

    public SearchLog(Integer slId, String slContent, Integer slSearchCount) {
        this.slId = slId;
        this.slContent = slContent;
        this.slSearchCount = slSearchCount;
    }

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }

    public String getSlContent() {
        return slContent;
    }

    public void setSlContent(String slContent) {
        this.slContent = slContent == null ? null : slContent.trim();
    }

    public Integer getSlSearchCount() {
        return slSearchCount;
    }

    public void setSlSearchCount(Integer slSearchCount) {
        this.slSearchCount = slSearchCount;
    }
}