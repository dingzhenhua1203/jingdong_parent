package com.jingdong.service.system;

import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.system.Admin;

import java.util.List;
import java.util.Map;

/**
 * 资源业务逻辑层
 */
public interface ResourceService {



    public List<String> findResKeyByLoginName(String  loginName);


}
