package com.fh.service;

import com.fh.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> queryProductByIsHot();

    Product getProductById(Integer productId);

    Long updateProductStock(Integer productId, Long count);
}
