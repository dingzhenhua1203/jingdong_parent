package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.goods.service.CategoryService;
import com.jingdong.pojo.business.Ad;
import com.jingdong.service.business.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Reference
    private AdService adService;

    @Reference
    private CategoryService categoryService;

    @GetMapping("index")
    public String index(Model model) {
        List<Ad> data = adService.findByPosition("web_index_lb");
        model.addAttribute("lunbotu", data);

        List<Map> categoryTree = categoryService.findCategoryTree();
        model.addAttribute("categoryTree", categoryTree);
        return "index";
    }

    @ResponseBody
    @GetMapping("test")
    public List<Map> testData(Model model) {
        List<Map> categoryTree = categoryService.findCategoryTree();

        return categoryTree;
    }
}
