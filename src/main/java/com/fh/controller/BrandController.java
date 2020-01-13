package com.fh.controller;

import com.fh.common.ServerResponse;
import com.fh.model.Brand;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand")
    public ServerResponse queryBrandByIsHot(){
        List<Brand> brandList = brandService.queryBrandByIsHot();
        return ServerResponse.success(brandList);
    }

    public static void main(String[] args) {
        String s = "李王张刘好飞狐教育";

        String substring = s.substring(3, 5);
        System.out.println(substring);
    }

}
