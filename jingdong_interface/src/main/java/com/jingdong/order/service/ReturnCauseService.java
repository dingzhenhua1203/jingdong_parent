package com.jingdong.order.service;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.order.ReturnCause;

import java.util.*;

/**
 * returnCause业务逻辑层
 */
public interface ReturnCauseService {


    public List<ReturnCause> findAll();


    public PageResult<ReturnCause> findPage(int page, int size);


    public List<ReturnCause> findList(Map<String, Object> searchMap);


    public PageResult<ReturnCause> findPage(Map<String, Object> searchMap, int page, int size);


    public ReturnCause findById(Integer id);

    public void add(ReturnCause returnCause);


    public void update(ReturnCause returnCause);


    public void delete(Integer id);

}
