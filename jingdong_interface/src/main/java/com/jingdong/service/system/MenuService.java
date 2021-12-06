package com.jingdong.service.system;
import com.jingdong.model.base.PageResult;
import com.jingdong.pojo.system.Menu;

import java.util.*;

/**
 * menu业务逻辑层
 */
public interface MenuService {


    public List<Menu> findAll();


    public PageResult<Menu> findPage(int page, int size);


    public List<Menu> findList(Map<String, Object> searchMap);


    public PageResult<Menu> findPage(Map<String, Object> searchMap, int page, int size);


    public Menu findById(String id);

    public void add(Menu menu);


    public void update(Menu menu);


    public void delete(String id);


    /**
     * 获取全部菜单
     * @return
     */
    public List<Map> findAllMenu();

    /**
     * 根据登陆名获取菜单列表
     * @return
     */
    public List<Map> findMenuListByLoginName(String loginName);

}
