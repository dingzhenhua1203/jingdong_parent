package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.goods.service.CategoryService;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Category;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Category> findPage(int page, int size){
        return categoryService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Category> findList(@RequestBody Map<String,Object> searchMap){
        return categoryService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Category> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  categoryService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Category findById(Integer id){
        return categoryService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Category category) {
        categoryService.add(category);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Category category) {
        categoryService.update(category);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Integer id) {
        categoryService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
