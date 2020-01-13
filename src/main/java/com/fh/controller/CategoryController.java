package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.*;
import com.fh.service.CategoryService;
import com.fh.util.JedisConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ServerResponse queryCategoryList(){
        List<Category> categoryList = categoryService.queryCategoryList();
        return ServerResponse.success(categoryList);
    }




}
