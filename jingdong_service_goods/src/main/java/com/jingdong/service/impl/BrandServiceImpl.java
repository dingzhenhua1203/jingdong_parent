package com.jingdong.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jingdong.dao.BrandMapper;
import com.jingdong.pojo.goods.Brand;
import com.jingdong.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> listBrands() {
        List<Brand> result=brandMapper.selectAll();
        return result;
    }
}
