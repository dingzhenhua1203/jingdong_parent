package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.order.ReturnCause;
import com.jingdong.service.order.ReturnCauseService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/returnCause")
public class ReturnCauseController {

    @Reference
    private ReturnCauseService returnCauseService;

    @GetMapping("/findAll")
    public List<ReturnCause> findAll(){
        return returnCauseService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ReturnCause> findPage(int page, int size){
        return returnCauseService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ReturnCause> findList(@RequestBody Map<String,Object> searchMap){
        return returnCauseService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ReturnCause> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  returnCauseService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ReturnCause findById(Integer id){
        return returnCauseService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody ReturnCause returnCause){
        returnCauseService.add(returnCause);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody ReturnCause returnCause){
        returnCauseService.update(returnCause);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        returnCauseService.delete(id);
        return new ResultMsg();
    }

}
