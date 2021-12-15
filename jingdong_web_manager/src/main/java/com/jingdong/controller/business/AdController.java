package com.jingdong.controller.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.business.Ad;
import com.jingdong.service.business.AdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Reference
    private AdService adService;

    @GetMapping("/findAll")
    public List<Ad> findAll(){
        return adService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Ad> findPage(int page, int size){
        return adService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Ad> findList(@RequestBody Map<String,Object> searchMap){
        return adService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Ad> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  adService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Ad findById(Integer id){
        return adService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Ad ad){
        adService.add(ad);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Ad ad){
        adService.update(ad);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        adService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
