package com.jingdong.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jingdong.service.goods.CategoryService;
import com.jingdong.service.goods.SkuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化服务类
 */
@Service
public class InitService implements InitializingBean {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SkuService skuService;

    public void afterPropertiesSet() throws Exception {
        System.out.println("---缓存预热---");
        categoryService.saveCategoryTreeToRedis();//加载商品分类导航缓存
        skuService.saveAllPriceToRedis();//加载价格数据
    }
}
