package com.jingdong.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.system.Menu;
import com.jingdong.service.system.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @GetMapping("/findAll")
    public List<Menu> findAll(){
        return menuService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Menu> findPage(int page, int size){
        return menuService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Menu> findList(@RequestBody Map<String,Object> searchMap){
        return menuService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Menu> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  menuService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Menu findById(String id){
        return menuService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Menu menu){
        menuService.add(menu);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Menu menu){
        menuService.update(menu);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(String id){
        menuService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/findMenu")
    public List<Map> findMenu(){
        return menuService.findAllMenu();
    }

}
