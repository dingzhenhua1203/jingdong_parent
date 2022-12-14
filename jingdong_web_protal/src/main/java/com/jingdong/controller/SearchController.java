package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.goods.service.SkuSearchService;
import com.jingdong.pojo.business.Ad;
import com.jingdong.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Reference
    private SkuSearchService skuSearchService;

    @GetMapping("search")
    public String index(Model model, @RequestParam Map<String, String> searchMap) throws Exception {
        // 字符集处理
        searchMap = WebUtil.convertCharsetToUTF8(searchMap);
        Map result = skuSearchService.search(searchMap);
        model.addAttribute("result", result);

        return "search";

    }

    /**
     * 按照一级分类查出sku写入es中
     *
     * @param firstCategoryIds
     * @return
     */
    @ResponseBody
    @GetMapping("pushes")
    public String PushES(String firstCategoryIds) {

        List<String> firstCategoryIdList = Arrays.stream(firstCategoryIds.split(",")).collect(Collectors.toList());
        skuSearchService.importSkuList(firstCategoryIdList);
        return "search";
    }
}
