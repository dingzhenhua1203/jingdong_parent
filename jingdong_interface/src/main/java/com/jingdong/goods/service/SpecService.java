package com.jingdong.goods.service;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.goods.Spec;

import java.util.*;

/**
 * 业务逻辑层
 */
public interface SpecService {


    public List<Spec> findAll();


    public PageResult<Spec> findPage(int page, int size);


    public List<Spec> findList(Map<String,Object> searchMap);


    public PageResult<Spec> findPage(Map<String,Object> searchMap,int page, int size);


    public Spec findById(Integer id);

    public void add(Spec spec);


    public void update(Spec spec);


    public void delete(Integer id);

}