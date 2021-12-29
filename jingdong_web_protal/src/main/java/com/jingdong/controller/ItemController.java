package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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

        // Spu spu=spuService.findById(spuId); // goods中包含了
        // 查询商品信息
        GoodsDto goodsDto = spuService.findGoodsById(spuId);

        List<Sku> skuList = goodsDto.getSkuList();

        // 三级分类名称 面包屑用
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add(categoryService.findById(goodsDto.getSpu().getCategory1Id()).getName());
        categoryNames.add(categoryService.findById(goodsDto.getSpu().getCategory2Id()).getName());
        categoryNames.add(categoryService.findById(goodsDto.getSpu().getCategory3Id()).getName());


        for (Sku item : skuList) {
            // 创建上下文
            Context context = new Context();
            Map<String, Object> templateDataModel = new HashMap<>();
            templateDataModel.put("spu", goodsDto.getSpu());
            templateDataModel.put("sku", item);
            templateDataModel.put("categoryNames", categoryNames);
            templateDataModel.put("skuImages", item.getImages().split(","));
            String[] sss = goodsDto.getSpu().getImages().split(",");
            if (sss != null && sss.length > 0) {
                templateDataModel.put("spuImages", sss);
            }

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
