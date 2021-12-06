package com.jingdong.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.goods.ListBrandsRequest;
import com.jingdong.pojo.goods.Brand;
import com.jingdong.service.goods.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @GetMapping("/list-all-brand")
    public List<Brand> listAllBrands() {
        return brandService.listAllBrands();
    }

    @PostMapping("/list-brand")
    public PageResult<Brand> listBrands(@RequestBody ListBrandsRequest request) {
        return brandService.listBrands(request);
    }

    @GetMapping("/preview-detail")
    public Brand previewDetail(@RequestParam(name = "id", required = true) Integer id) {
        return brandService.previewDetail(id);
    }

    @PostMapping("/upsert-brand")
    public boolean UpsertBrand(@RequestBody Brand brand) {
        return brandService.upsertBrand(brand);
    }

    @PostMapping("/del-brand")
    public boolean DelBrand(@RequestParam(name = "id", required = true) Integer id) {
        return brandService.delBrand(id);
    }
}
