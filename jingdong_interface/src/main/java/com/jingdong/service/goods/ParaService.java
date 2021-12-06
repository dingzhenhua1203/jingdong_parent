package com.jingdong.service.goods;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.goods.Para;

import java.util.*;

/**
 * 业务逻辑层
 */
public interface ParaService {


    public List<Para> findAll();


    public PageResult<Para> findPage(int page, int size);


    public List<Para> findList(Map<String,Object> searchMap);


    public PageResult<Para> findPage(Map<String,Object> searchMap,int page, int size);


    public Para findById(Integer id);

    public void add(Para para);


    public void update(Para para);


    public void delete(Integer id);

}
