package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.pojo.system.Admin;
import com.jingdong.service.system.AdminService;
import com.jingdong.service.system.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.*;

@SuppressWarnings("unchecked")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private AdminService adminService;

    @Reference
    private ResourceService resourceService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询管理员
        Map map= new HashMap();
        map.put("loginName",s);
        map.put("status","1");
        List<Admin> list = adminService.findList(map);
        if(list==null || list.isEmpty()){
            return  null;
        }

        //构建权限列表
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

        /*List<String> resKeyList = resourceService.findResKeyByLoginName(s);
        for(String resKey :resKeyList ){
            grantedAuths.add(new SimpleGrantedAuthority(resKey));
        }*/
        grantedAuths.add( new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(s,list.get(0).getPassword(),grantedAuths);
    }
}
