package com.jingdong.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.user.Areas;
import com.jingdong.service.user.AreasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/areas")
public class AreasController {

    @Reference
    private AreasService areasService;

    @GetMapping("/findAll")
    public List<Areas> findAll(){
        return areasService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Areas> findPage(int page, int size){
        return areasService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Areas> findList(@RequestBody Map<String,Object> searchMap){
        return areasService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Areas> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  areasService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Areas findById(String areaid){
        return areasService.findById(areaid);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Areas areas){
        areasService.add(areas);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Areas areas){
        areasService.update(areas);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(String areaid){
        areasService.delete(areaid);
        return ResultMsg.SuccessResult(true);
    }

}
