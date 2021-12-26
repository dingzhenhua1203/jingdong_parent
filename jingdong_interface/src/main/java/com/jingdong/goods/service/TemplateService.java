package com.jingdong.goods.service;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.goods.Template;

import java.util.*;

/**
 * 业务逻辑层
 */
public interface TemplateService {


    public List<Template> findAll();


    public PageResult<Template> findPage(int page, int size);


    public List<Template> findList(Map<String,Object> searchMap);


    public PageResult<Template> findPage(Map<String,Object> searchMap,int page, int size);


    public Template findById(Integer id);

    public void add(Template template);


    public void update(Template template);


    public void delete(Integer id);

}
