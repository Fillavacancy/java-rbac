package com.jrbac.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jrbac.dao.CsdnBlogDao;
import com.jrbac.service.impl.CsdnBlogPageProcessorServiceImpl;

@Controller
@RequestMapping(value = "/admin/csdnblog")
public class CsdnBlogController {
    @Autowired
    private CsdnBlogPageProcessorServiceImpl blogPageProcessorServiceImpl;
    @Autowired
    private CsdnBlogDao                      csdnBlogDao;

    /**
     * CSDN博客爬虫
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/crawlerrun", method = RequestMethod.POST)
    public String crawlerRun() {
        return blogPageProcessorServiceImpl.crawlerRun(csdnBlogDao);
    }
}
