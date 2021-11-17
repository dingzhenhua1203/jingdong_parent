package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.pojo.goods.Brand;
import com.jingdong.service.goods.BrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @GetMapping("/list-brand")
    public  List<Brand> listBrands(){
        return brandService.listBrands();
    }
}
