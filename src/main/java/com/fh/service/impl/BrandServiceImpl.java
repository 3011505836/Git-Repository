package com.fh.service.impl;

import com.fh.mapper.BrandMapper;
import com.fh.mapper.CategoryMapper;
import com.fh.model.Brand;
import com.fh.model.Category;
import com.fh.service.BrandService;
import com.fh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> queryBrandByIsHot() {
        return brandMapper.queryBrandByIsHot();
    }
}
