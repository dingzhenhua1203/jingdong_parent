package com.jingdong.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.system.Role;
import com.jingdong.service.system.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    @GetMapping("/findAll")
    public List<Role> findAll(){
        return roleService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Role> findPage(int page, int size){
        return roleService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Role> findList(@RequestBody Map<String,Object> searchMap){
        return roleService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Role> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  roleService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Role findById(Integer id){
        return roleService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Role role){
        roleService.add(role);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Role role){
        roleService.update(role);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        roleService.delete(id);
        return new ResultMsg();
    }

}
