package com.jingdong.order.service;

import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.order.ReturnOrderItem;

import java.util.List;
import java.util.Map;

/**
 * returnOrderItem业务逻辑层
 */
public interface ReturnOrderItemService {


    public List<ReturnOrderItem> findAll();


    public PageResult<ReturnOrderItem> findPage(int page, int size);


    public List<ReturnOrderItem> findList(Map<String,Object> searchMap);


    public PageResult<ReturnOrderItem> findPage(Map<String,Object> searchMap,int page, int size);


    public ReturnOrderItem findById(Long id);

    public void add(ReturnOrderItem returnOrderItem);


    public void update(ReturnOrderItem returnOrderItem);


    public void delete(Long id);

}
