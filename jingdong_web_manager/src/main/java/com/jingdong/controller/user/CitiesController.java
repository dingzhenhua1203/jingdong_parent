package com.jingdong.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.user.Cities;
import com.jingdong.service.user.CitiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Reference
    private CitiesService citiesService;

    @GetMapping("/findAll")
    public List<Cities> findAll(){
        return citiesService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Cities> findPage(int page, int size){
        return citiesService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Cities> findList(@RequestBody Map<String,Object> searchMap){
        return citiesService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Cities> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  citiesService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Cities findById(String cityid){
        return citiesService.findById(cityid);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Cities cities){
        citiesService.add(cities);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Cities cities){
        citiesService.update(cities);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(String cityid){
        citiesService.delete(cityid);
        return ResultMsg.SuccessResult(true);
    }

}
