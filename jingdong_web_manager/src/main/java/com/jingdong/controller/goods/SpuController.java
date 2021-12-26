package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.model.goods.GoodsDto;
import com.jingdong.pojo.goods.Spu;
import com.jingdong.goods.service.SpuService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Reference
    private SpuService spuService;

    @GetMapping("/findAll")
    public List<Spu> findAll(){
        return spuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Spu> findPage(int page, int size){
        return spuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Spu> findList(@RequestBody Map<String,Object> searchMap){
        return spuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Spu> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  spuService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Spu findById(String id){
        return spuService.findById(id);
    }


    @PostMapping("/save")
    public ResultMsg save(@RequestBody GoodsDto goods){
        spuService.saveGoods(goods);
        return new ResultMsg();
    }


    @GetMapping("/delete")
    public ResultMsg delete(String id){
        spuService.delete(id);
        return new ResultMsg();
    }

    @GetMapping("/logicDelete")
    public ResultMsg logicDelete(String id){
        spuService.delete(id);
        return new ResultMsg();
    }

    @GetMapping("/restore")
    public ResultMsg restore(String id){
        spuService.restore(id);
        return new ResultMsg();
    }

    @GetMapping("/audit")
    public ResultMsg audit(String id){
        spuService.audit(id);
        return new ResultMsg();
    }


    @GetMapping("/pull")
    public ResultMsg pull(String id){
        spuService.pull(id);
        return new ResultMsg();
    }

    @GetMapping("/put")
    public ResultMsg put(String id){
        spuService.put(id);
        return new ResultMsg();
    }

    @GetMapping("/putMany")
    public ResultMsg putMany(String[] ids){
        int count = spuService.putMany(ids);
        return new ResultMsg(0,"上架"+count+"个商品");
    }

    @GetMapping("/findGoodsById")
    public GoodsDto findGoodsById(String id){
        return spuService.findGoodsById(id);
    }




}
