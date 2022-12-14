package com.jingdong.order.service;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.order.OrderLog;

import java.util.*;

/**
 * orderLog业务逻辑层
 */
public interface OrderLogService {


    public List<OrderLog> findAll();


    public PageResult<OrderLog> findPage(int page, int size);


    public List<OrderLog> findList(Map<String, Object> searchMap);


    public PageResult<OrderLog> findPage(Map<String, Object> searchMap, int page, int size);


    public OrderLog findById(Long id);

    public void add(OrderLog orderLog);


    public void update(OrderLog orderLog);


    public void delete(Long id);

}
