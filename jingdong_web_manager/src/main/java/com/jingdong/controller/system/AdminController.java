package com.jingdong.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.system.Admin;
import com.jingdong.service.system.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Reference
    private AdminService adminService;

    @GetMapping("/findAll")
    public List<Admin> findAll(){
        return adminService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Admin> findPage(int page, int size){
        return adminService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Admin> findList(@RequestBody Map<String,Object> searchMap){
        return adminService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Admin> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  adminService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Admin findById(Integer id){
        return adminService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Admin admin){
        adminService.add(admin);
        return new ResultMsg();
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Admin admin){
        adminService.update(admin);
        return new ResultMsg();
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id){
        adminService.delete(id);
        return new ResultMsg();
    }

    /**
     * 保存管理员角色
     * @param adminId
     * @param roleIds
     */
    @PostMapping("/saveAdminRoles")
    public ResultMsg saveAdminRoles(Integer adminId,@RequestBody Integer [] roleIds){
        adminService.saveAdminRoles(adminId,roleIds);
        return new ResultMsg();
    }


}
