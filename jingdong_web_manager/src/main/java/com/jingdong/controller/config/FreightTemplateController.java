package com.jingdong.controller.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.config.FreightTemplate;
import com.jingdong.service.config.FreightTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/freightTemplate")
public class FreightTemplateController {

    @Reference
    private FreightTemplateService freightTemplateService;

    @GetMapping("/findAll")
    public List<FreightTemplate> findAll(){
        return freightTemplateService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<FreightTemplate> findPage(int page, int size){
        return freightTemplateService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<FreightTemplate> findList(@RequestBody Map<String,Object> searchMap){
        return freightTemplateService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<FreightTemplate> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  freightTemplateService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public FreightTemplate findById(Integer id){
        return freightTemplateService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody FreightTemplate freightTemplate){
        freightTemplateService.add(freightTemplate);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody FreightTemplate freightTemplate){
        freightTemplateService.update(freightTemplate);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        freightTemplateService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
