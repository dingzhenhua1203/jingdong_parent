package com.jingdong.controller.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.business.Activity;
import com.jingdong.service.business.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Reference
    private ActivityService activityService;

    @GetMapping("/findAll")
    public List<Activity> findAll(){
        return activityService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Activity> findPage(int page, int size){
        return activityService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Activity> findList(@RequestBody Map<String,Object> searchMap){
        return activityService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Activity> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  activityService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Activity findById(Integer id){
        return activityService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Activity activity){
        activityService.add(activity);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Activity activity){
        activityService.update(activity);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        activityService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
