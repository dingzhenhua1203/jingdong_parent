package com.jingdong.goods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.jingdong.goods.dao.SkuMapper;
import com.jingdong.goods.service.SkuSearchService;
import com.jingdong.model.goods.GoodsESDataModel;
import com.jingdong.pojo.goods.Sku;
import com.jingdong.util.es.ESHighLevelRestUtil;
import com.jingdong.util.es.ESQueryBuilderUtil;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private SkuMapper skuMapper;

    private static Integer pageSize = 30;

    /**
     * 按照spuid将每个spu下的sku写入es
     *
     * @param firstCategoryIds
     */
    @Override
    public void importSkuList(List<String> firstCategoryIds) {
        // 1.查询要写入es的数据源
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("categoryId", firstCategoryIds);
        List<Sku> skuList = skuMapper.selectByExample(example);
        List<GoodsESDataModel> esSources = new ArrayList<>();
        // 2.整理数据结构
        for (Sku sku : skuList) {
            GoodsESDataModel skuEsModel = new GoodsESDataModel();
            skuEsModel.goodsId = sku.getId();
            skuEsModel.goodsName = sku.getName();
            skuEsModel.goodsImg = sku.getImage();
            skuEsModel.price = sku.getPrice();
            skuEsModel.createDate = sku.getCreateTime();
            skuEsModel.categoryName = sku.getCategoryName();
            skuEsModel.brandName = sku.getBrandName();
            skuEsModel.spec = JSON.parseObject(sku.getSpec());
            skuEsModel.stocksNumber = sku.getNum();
            skuEsModel.salesNumber = sku.getSaleNum();
            skuEsModel.commentNumber = sku.getCommentNum();
            esSources.add(skuEsModel);
        }

        // 3.写入es
        try {
            Boolean flag = ESHighLevelRestUtil.batchIndexCreate("goodsdata", esSources);
        } catch (Exception ex) {
            System.out.println("ex" + ex.toString());
        }
    }

    @Override
    public Map search(Map<String, String> searchMap) {
        try {
            String sortKey = searchMap.getOrDefault("sort", "");
            SortOrder sortOrder = SortOrder.valueOf(searchMap.getOrDefault("sortOrder", "ASC"));

            ESQueryBuilderUtil esQueryBuilderUtil = new ESQueryBuilderUtil();
            SearchSourceBuilder sourceBuilder;
            sourceBuilder = esQueryBuilderUtil.matchQuery("goodsName", searchMap.getOrDefault("keywords", ""))
                    .termQuery("categoryName", searchMap.getOrDefault("categoryName", ""))
                    .termQuery("brandName", searchMap.getOrDefault("brandName", ""))
                    .sorting(sortKey, sortOrder)
                    .useHighLight("goodsName")
                    .paging(Integer.parseInt(searchMap.getOrDefault("pageIndex", "0")), pageSize)
                    // .aggregationQuery("categoryName")
                    .execute();
            // 获取es数据
            Map result = ESHighLevelRestUtil.boolQuery("goodsdata", sourceBuilder, "categoryName");

            // 返回用户选择的筛选条件项
            Map<String, String> chooses = new HashMap<>();
            if (searchMap.containsKey("categoryName")) {
                chooses.put("商品分类：" + searchMap.get("categoryName"), "&categoryName=" + searchMap.get("categoryName"));
            }
            if (searchMap.containsKey("brandName")) {
                chooses.put("品牌：" + searchMap.get("brandName"), "&brandName=" + searchMap.get("brandName"));
            }
            result.put("chooses", chooses);


            // 计算查询结果有多少页
            Long totals = Long.parseLong(result.get("totals").toString());
            Long totalPages = totals % pageSize == 0 ? (totals / pageSize) : (totals / pageSize) + 1;

            result.put("totalPages", totalPages);
            result.put("currentPage", Integer.parseInt(searchMap.getOrDefault("pageIndex", "0")));
            return result;
        } catch (Exception ex) {
            System.out.println("ex" + ex.toString());
        }
        return null;
    }

    private void testES() {
        try {
            ESHighLevelRestUtil.boolQuery("goodsdata", "goodsName", "手机", "categoryName");
        } catch (Exception ex) {
            System.out.println("ex" + ex.toString());
        }
    }
}
