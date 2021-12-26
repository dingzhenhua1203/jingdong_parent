package com.jingdong.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.order.ReturnOrder;
import com.jingdong.order.service.ReturnOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/returnOrder")
public class ReturnOrderController {

    @Reference
    private ReturnOrderService returnOrderService;

    @GetMapping("/findAll")
    public List<ReturnOrder> findAll(){
        return returnOrderService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ReturnOrder> findPage(int page, int size){
        return returnOrderService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ReturnOrder> findList(@RequestBody Map<String,Object> searchMap){
        return returnOrderService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ReturnOrder> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  returnOrderService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ReturnOrder findById(Long id){
        return returnOrderService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody ReturnOrder returnOrder){
        returnOrderService.add(returnOrder);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody ReturnOrder returnOrder){
        returnOrderService.update(returnOrder);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Long id){
        returnOrderService.delete(id);
        return new ResultMsg();
    }


    /**
     * 同意退款
     * @param id
     * @param money
     */
    public ResultMsg agreeRefund(Long id, Integer money){
        Integer adminId=0;//获取当前登陆人ID
        returnOrderService.agreeRefund(id,money,adminId);
        return new ResultMsg();
    }

    /**
     * 驳回退款
     * @param id
     * @param remark
     */
    public ResultMsg rejectRefund(Long id, String remark){
        Integer adminId=0;//获取当前登陆人ID
        returnOrderService.rejectRefund(id,remark,adminId);
        return new ResultMsg();
    }


}
