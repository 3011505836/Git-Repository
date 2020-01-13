package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Product;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ServerResponse queryProductByIsHot(){
        List<Product> productList = productService.queryProductByIsHot();
        return ServerResponse.success(productList);
    }




}
