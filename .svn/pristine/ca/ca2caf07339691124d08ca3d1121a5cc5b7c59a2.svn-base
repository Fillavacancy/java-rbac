package com.jrbac.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jrbac.dao.BrandDao;
import com.jrbac.entity.Brand;
import com.jrbac.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandMapper;

    /**
     * 查询所有品牌
     * 
     * @return
     */
    @Override
    public List<Brand> selectBrandList() {
        return brandMapper.selectBrandList();
    }

    @Override
    public Brand selectById(Integer brId) {
        return brandMapper.selectById(brId);
    }
}
