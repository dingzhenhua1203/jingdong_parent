package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Template;
import com.jingdong.goods.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    @Reference
    private TemplateService templateService;

    @GetMapping("/findAll")
    public List<Template> findAll(){
        return templateService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Template> findPage(int page, int size){
        return templateService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Template> findList(@RequestBody Map<String,Object> searchMap){
        return templateService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Template> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  templateService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Template findById(Integer id){
        return templateService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Template template){
        templateService.add(template);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Template template){
        templateService.update(template);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        templateService.delete(id);
        return new ResultMsg();
    }

}
