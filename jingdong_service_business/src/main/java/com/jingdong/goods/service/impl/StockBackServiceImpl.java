package com.jingdong.goods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jingdong.goods.dao.SkuMapper;
import com.jingdong.goods.dao.StockBackMapper;
import com.jingdong.pojo.goods.StockBack;
import com.jingdong.pojo.order.OrderItem;
import com.jingdong.goods.service.StockBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(interfaceClass = StockBackService.class)
public class StockBackServiceImpl implements StockBackService {

    @Autowired
    private StockBackMapper stockBackMapper;

    @Override
    public void add(StockBack stockBack) {
        stockBackMapper.insert(stockBack);
    }

    @Override
    @Transactional
    public void addList(List<OrderItem> orderItems) {
        for(OrderItem orderItem:orderItems){
            StockBack stockBack=new StockBack();
            stockBack.setOrderId(orderItem.getOrderId());
            stockBack.setSkuId(orderItem.getSkuId());
            stockBack.setStatus("0");
            stockBack.setNum(orderItem.getNum());
            stockBack.setCreateTime(new Date());
            stockBackMapper.insert(stockBack);
        }
    }

    @Autowired
    private SkuMapper skuMapper;

    @Override
    @Transactional
    public void doBack() {
        StockBack stockBack0=new StockBack();
        stockBack0.setStatus("0");
        List<StockBack> stockBacks = stockBackMapper.selectByExample(stockBack0);
        for(StockBack stockBack:stockBacks ){
            skuMapper.updateNum(stockBack.getSkuId(),stockBack.getNum());
            stockBack.setBackTime(new Date());
            stockBack.setStatus("1");
            stockBackMapper.updateByPrimaryKey(stockBack);
        }
    }
}
