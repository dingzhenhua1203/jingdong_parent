package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Pref;
import com.jingdong.goods.service.PrefService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/pref")
@CrossOrigin
public class PrefController {

    @Reference
    private PrefService prefService;

    @GetMapping("/findAll")
    public List<Pref> findAll(){
        return prefService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Pref> findPage(int page, int size){
        return prefService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Pref> findList(@RequestBody Map<String,Object> searchMap){
        return prefService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Pref> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  prefService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Pref findById(Integer id){
        return prefService.findById(id);
    }

    @PostMapping("/add")
    public ResultMsg add(@RequestBody Pref pref){
        prefService.add(pref);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Pref pref){
        prefService.update(pref);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        prefService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
