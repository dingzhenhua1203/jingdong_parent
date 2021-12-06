package com.jingdong.service.user;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.user.Provinces;

import java.util.*;

/**
 * provinces业务逻辑层
 */
public interface ProvincesService {


    public List<Provinces> findAll();


    public PageResult<Provinces> findPage(int page, int size);


    public List<Provinces> findList(Map<String, Object> searchMap);


    public PageResult<Provinces> findPage(Map<String, Object> searchMap, int page, int size);


    public Provinces findById(String provinceid);

    public void add(Provinces provinces);


    public void update(Provinces provinces);


    public void delete(String provinceid);

}
