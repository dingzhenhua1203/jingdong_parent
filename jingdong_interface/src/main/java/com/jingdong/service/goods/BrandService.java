package com.jingdong.service.goods;

import com.jingdong.model.base.PageResult;
import com.jingdong.model.goods.ListBrandsRequest;
import com.jingdong.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<Brand> listAllBrands();

    PageResult<Brand> listBrands(ListBrandsRequest request);

    Brand previewDetail(Integer id);
    boolean upsertBrand(Brand brand);
    boolean delBrand(Integer id);
}
