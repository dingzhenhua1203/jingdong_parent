package com.jingdong.service.goods;

import com.jingdong.model.base.PageResult;
import com.jingdong.model.goods.ListBrandsRequest;
import com.jingdong.pojo.goods.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> listAllBrands();

    PageResult<Brand> listBrands(ListBrandsRequest request);

    boolean upsertBrand(Brand brand);
    boolean delBrand(Integer id);
}
