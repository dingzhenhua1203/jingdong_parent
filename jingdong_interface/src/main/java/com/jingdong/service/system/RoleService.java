package com.jingdong.service.system;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.system.Role;

import java.util.*;

/**
 * role业务逻辑层
 */
public interface RoleService {


    public List<Role> findAll();


    public PageResult<Role> findPage(int page, int size);


    public List<Role> findList(Map<String, Object> searchMap);


    public PageResult<Role> findPage(Map<String, Object> searchMap, int page, int size);


    public Role findById(Integer id);

    public void add(Role role);


    public void update(Role role);


    public void delete(Integer id);

}
