package com.jingdong.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.system.Resource;
import com.jingdong.service.system.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Reference
    private ResourceService resourceService;

    @GetMapping("/findAll")
    public List<Resource> findAll(){
        return resourceService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Resource> findPage(int page, int size){
        return resourceService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Resource> findList(@RequestBody Map<String,Object> searchMap){
        return resourceService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Resource> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  resourceService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Resource findById(Integer id){
        return resourceService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Resource resource){
        resourceService.add(resource);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Resource resource){
        resourceService.update(resource);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        resourceService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
