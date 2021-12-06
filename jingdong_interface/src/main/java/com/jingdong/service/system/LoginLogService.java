package com.jingdong.service.system;

import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.system.LoginLog;

import java.util.List;
import java.util.Map;

/**
 * loginLog业务逻辑层
 */
public interface LoginLogService {


    public List<LoginLog> findAll();


    public PageResult<LoginLog> findPage(int page, int size);


    public List<LoginLog> findList(Map<String, Object> searchMap);


    public PageResult<LoginLog> findPage(Map<String, Object> searchMap, int page, int size);


    public LoginLog findById(Integer id);

    public void add(LoginLog loginLog);


    public void update(LoginLog loginLog);


    public void delete(Integer id);

}
