package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.pojo.business.Ad;
import com.jingdong.service.business.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Reference
    private AdService adService;

    @GetMapping("index")
    public String index(Model model) {
        List<Ad> data = adService.findByPosition("web_index_lb");
        model.addAttribute("lunbotu",data);
        return  "index";
    }
}
