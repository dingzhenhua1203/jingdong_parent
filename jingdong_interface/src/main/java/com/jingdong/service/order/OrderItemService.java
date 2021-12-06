package com.jingdong.service.order;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.order.CategoryReport;
import com.jingdong.pojo.order.OrderItem;

import java.time.LocalDate;
import java.util.*;

/**
 * orderItem业务逻辑层
 */
public interface OrderItemService {


    public List<OrderItem> findAll();


    public PageResult<OrderItem> findPage(int page, int size);


    public List<OrderItem> findList(Map<String, Object> searchMap);


    public PageResult<OrderItem> findPage(Map<String, Object> searchMap, int page, int size);


    public OrderItem findById(String id);

    public void add(OrderItem orderItem);


    public void update(OrderItem orderItem);


    public void delete(String id);



}
