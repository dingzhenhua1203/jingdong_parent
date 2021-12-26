package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.goods.service.AlbumService;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.pojo.goods.Album;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    @Reference
    private AlbumService albumService;

    @GetMapping("/findAll")
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Album> findPage(int page, int size) {
        return albumService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map<String, Object> searchMap) {
        return albumService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Album> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return albumService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Album findById(Long id) {
        return albumService.findById(id);
    }


    @PostMapping("/add")
    public ResultMsg add(@RequestBody Album album) {
        albumService.add(album);
        return ResultMsg.SuccessResult(true);
    }

    @PostMapping("/update")
    public ResultMsg update(@RequestBody Album album) {
        albumService.update(album);
        return ResultMsg.SuccessResult(true);
    }

    @GetMapping("/delete")
    public ResultMsg delete(Long id) {
        albumService.delete(id);
        return ResultMsg.SuccessResult(true);
    }

}
