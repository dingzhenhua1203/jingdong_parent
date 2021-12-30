package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jingdong.goods.service.CategoryService;
import com.jingdong.goods.service.SkuService;
import com.jingdong.goods.service.SpuService;
import com.jingdong.model.goods.GoodsDto;
import com.jingdong.pojo.goods.Sku;
import com.jingdong.pojo.goods.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("item")
@RestController
public class ItemController {

    @Reference
    private SpuService spuService;

    @Reference
    private SkuService skuService;

    @Reference
    private CategoryService categoryService;

    // 注入配置文件中配置的商品模板路径， 因为未来发布linux和window地址是不一样的，需要配置
    @Value("${templatePagePath}")
    private String templatePagePath;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/gen-goods-templates")
    public void genGoodsStaticTemplatePages(String spuId) {


        // 查询商品信息
        GoodsDto goodsDto = spuService.findGoodsById(spuId);

        // spuService.findById(spuId); // goods中包含了
        Spu spu = goodsDto.getSpu();
        List<Sku> skuList = goodsDto.getSkuList();

        // 三级分类名称 面包屑用
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add(categoryService.findById(spu.getCategory1Id()).getName());
        categoryNames.add(categoryService.findById(spu.getCategory2Id()).getName());
        categoryNames.add(categoryService.findById(spu.getCategory3Id()).getName());


        for (Sku item : skuList) {
            // 创建上下文
            Context context = new Context();
            Map<String, Object> templateDataModel = new HashMap<>();
            templateDataModel.put("spu", spu);
            templateDataModel.put("sku", item);
            // 面包屑的分类名称
            templateDataModel.put("categoryNames", categoryNames);
            // 商品图片
            templateDataModel.put("skuImages", item.getImages().split(","));
            // spu的参数列表
            Map paraItems = JSON.parseObject(spu.getParaItems());
            // sku的规格参数列表
            Map skuItems = JSON.parseObject(item.getSpec());
            templateDataModel.put("paraItems", paraItems);
            templateDataModel.put("skuItems", skuItems);
            // spu的图片
            String[] sss = goodsDto.getSpu().getImages().split(",");
            if (sss != null && sss.length > 0) {
                templateDataModel.put("spuImages", sss);
            }
            // 规格参数选择项
            // {"规格":["88片","80片","104片","96片"]}
            Map<String, List> specItems = (Map) JSON.parseObject(spu.getSpecItems());
            Map<String, List> new_specItems = new HashMap<>();
            // 还有一种遍历方式
            /*for (Map.Entry entry : specItems.entrySet()) {
                List<String> vals = (List)entry.getValue();
            }*/
            for (String key : specItems.keySet()) {
                List<String> vals = specItems.get(key);
                List<Map> newMap = vals.stream().map(x -> {
                    Map newRules = new HashMap();
                    newRules.put("option", x);
                    newRules.put("checked", skuItems.get(key).equals(x));
                    return newRules;
                }).collect(Collectors.toList());
                new_specItems.put(key, newMap);
            }
            templateDataModel.put("specItems", new_specItems);
            // item.getCategoryId();
            context.setVariables(templateDataModel);

            // 指定好路径，确认无误
            File dir = new File(templatePagePath);
            if (!dir.exists()) {
                // 如果路径不存在，则一层一层的创建文件夹
                dir.mkdirs();
            }

            // 写入文件
            File dest = new File(dir, item.getId() + ".html");

            try {
                PrintWriter printWriter = new PrintWriter(dest, "UTF-8");
                // 使用item模板 生成静态页面
                templateEngine.process("item", context, printWriter);
                System.out.println("生成页面");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }


    }


    @GetMapping("test")
    public String testPagePath() {
        return templatePagePath;
    }
}
