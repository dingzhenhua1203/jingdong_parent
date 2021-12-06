package com.jingdong.service.goods;

import com.jingdong.pojo.goods.StockBack;
import com.jingdong.pojo.order.OrderItem;

import java.util.List;

public interface StockBackService  {



    public void add(StockBack stockBack);


    public void addList(List<OrderItem> orderItems);

    /**
     *  执行库存回滚
     */
    public void doBack();

}
