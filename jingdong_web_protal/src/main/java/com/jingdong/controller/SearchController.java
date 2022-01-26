package com.jingdong.controller;

import com.jingdong.pojo.business.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @GetMapping("search")
    public String index(Model model) {

        return "search";
    }



    @ResponseBody
    @GetMapping("pushes")
    public String PushES() {

        return "search";
    }
}
