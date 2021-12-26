package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Para;
import com.jingdong.goods.service.ParaService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController {

    @Reference
    private ParaService paraService;

    @GetMapping("/findAll")
    public List<Para> findAll(){
        return paraService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Para> findPage(int page, int size){
        return paraService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Para> findList(@RequestBody Map<String,Object> searchMap){
        return paraService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Para> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  paraService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Para findById(Integer id){
        return paraService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Para para){
        paraService.add(para);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Para para){
        paraService.update(para);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        paraService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
