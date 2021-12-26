package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.order.OrderConfig;
import com.jingdong.order.service.OrderConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orderConfig")
public class OrderConfigController {

    @Reference
    private OrderConfigService orderConfigService;

    @GetMapping("/findAll")
    public List<OrderConfig> findAll(){
        return orderConfigService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<OrderConfig> findPage(int page, int size){
        return orderConfigService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<OrderConfig> findList(@RequestBody Map<String,Object> searchMap){
        return orderConfigService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<OrderConfig> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  orderConfigService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public OrderConfig findById(Integer id){
        return orderConfigService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody OrderConfig orderConfig){
        orderConfigService.add(orderConfig);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody OrderConfig orderConfig){
        orderConfigService.update(orderConfig);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        orderConfigService.delete(id);
        return new ResultMsg();
    }

}
