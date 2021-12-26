package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Sku;
import com.jingdong.goods.service.SkuService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Reference
    private SkuService skuService;

    @GetMapping("/findAll")
    public List<Sku> findAll(){
        return skuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Sku> findPage(int page, int size){
        return skuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Sku> findList(@RequestBody Map<String,Object> searchMap){
        return skuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Sku> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  skuService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Sku findById(String id){
        return skuService.findById(id);
    }

    @PostMapping("/add")
    public ResultMsg add(@RequestBody Sku sku){
        skuService.add(sku);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Sku sku){
        skuService.update(sku);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(String id){
        skuService.delete(id);
        return ResultMsg.SuccessResult(true);
    }


}
