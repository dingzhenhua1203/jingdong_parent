package com.jingdong.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 获取用户名
     *
     * @return
     */
    @GetMapping("/username")
    public Map username() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("当前登录用户：" + name);

        if("anonymousUser".equals(name)){ //未登录
            name="";
        }
        Map result = new HashMap();
        result.put("username", name);
        return result;
    }

}
