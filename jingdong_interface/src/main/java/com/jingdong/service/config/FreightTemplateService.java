package com.jingdong.service.config;

import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.config.FreightTemplate;

import java.util.List;
import java.util.Map;

/**
 * freightTemplate业务逻辑层
 */
public interface FreightTemplateService {


    public List<FreightTemplate> findAll();


    public PageResult<FreightTemplate> findPage(int page, int size);


    public List<FreightTemplate> findList(Map<String,Object> searchMap);


    public PageResult<FreightTemplate> findPage(Map<String,Object> searchMap,int page, int size);


    public FreightTemplate findById(Integer id);

    public void add(FreightTemplate freightTemplate);


    public void update(FreightTemplate freightTemplate);


    public void delete(Integer id);

}
