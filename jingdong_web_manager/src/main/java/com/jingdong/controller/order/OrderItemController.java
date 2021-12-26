package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.order.OrderItem;
import com.jingdong.order.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Reference
    private OrderItemService orderItemService;

    @GetMapping("/findAll")
    public List<OrderItem> findAll(){
        return orderItemService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<OrderItem> findPage(int page, int size){
        return orderItemService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<OrderItem> findList(@RequestBody Map<String,Object> searchMap){
        return orderItemService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  orderItemService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public OrderItem findById(String id){
        return orderItemService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody OrderItem orderItem){
        orderItemService.add(orderItem);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody OrderItem orderItem){
        orderItemService.update(orderItem);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(String id){
        orderItemService.delete(id);
        return new ResultMsg();
    }




}
