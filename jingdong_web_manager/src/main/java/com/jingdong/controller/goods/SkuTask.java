package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.service.goods.SkuService;
import com.jingdong.service.goods.StockBackService;
import com.jingdong.service.order.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SkuTask {

    @Reference
    private StockBackService stockBackService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void orderTimeOutLogic(){
        System.out.println("每2小数间隔执行一次任务"+ new Date());
        stockBackService.doBack();
    }

}
