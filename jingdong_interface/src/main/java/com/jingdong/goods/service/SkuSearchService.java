package com.jingdong.goods.service;

import com.jingdong.pojo.goods.Sku;

import java.util.List;
import java.util.Map;

public interface SkuSearchService {

    /**
     * 导入sku数据到es
     */
    public void importSkuList(List<String> firstCategoryIds);

    /**
     * 搜索数据
     * @param searchMap
     * @return
     */
    public Map search(Map<String, String> searchMap);

    /**
     * 查询分类列表
     * @param
     * @return
     */
    //public List<String> searchCategoryList(Map searchMap) ;

}
