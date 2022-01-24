package com.jingdong.goods.service.impl;

import com.jingdong.goods.service.SkuSearchService;
import com.jingdong.pojo.goods.Sku;
import com.jingdong.util.es.ESHighLevelFactory;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private RestHighLevelClient esClient;

    @Override
    public void importSkuList(List<Sku> sku) {

    }

    @Override
    public Map search(Map<String, String> searchMap) {
        return null;
    }
}
