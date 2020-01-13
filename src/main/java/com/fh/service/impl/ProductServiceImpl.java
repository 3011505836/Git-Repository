package com.fh.service.impl;

import com.fh.mapper.ProductMapper;
import com.fh.model.Product;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> queryProductByIsHot() {
        return productMapper.queryProductByIsHot();
    }


    @Override
    public Product getProductById(Integer productId) {
        return productMapper.getProductById(productId);
    }

    @Override
    public Long updateProductStock(Integer productId, Long count) {
        return productMapper.updateProductStock(productId,count);
    }
}
