package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.order.Order;
import com.jingdong.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Order> findPage(int page, int size){
        return orderService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map<String,Object> searchMap){
        return orderService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Order> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  orderService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Order findById(String id){
        return orderService.findById(id);
    }


   @PostMapping("/add")
    public ResultMsg add(@RequestBody Order order){
        orderService.add(order);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Order order){
        orderService.update(order);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(String id){
        orderService.delete(id);
        return new ResultMsg();
    }

    @PostMapping("/batchSend")
    public ResultMsg batchSend(@RequestBody List<Order> orders){
        orderService.batchSend(orders);
        return new ResultMsg();
    }

    @GetMapping("/orderTimeOutLogic")
    public ResultMsg orderTimeOutLogic(){
        orderService.orderTimeOutLogic();
        return new ResultMsg();
    }

}
