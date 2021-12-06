package com.jingdong.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jingdong.dao.BrandMapper;
import com.jingdong.model.base.PageResult;
import com.jingdong.model.goods.ListBrandsRequest;
import com.jingdong.pojo.goods.Brand;
import com.jingdong.service.goods.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> listAllBrands() {
        List<Brand> result = brandMapper.selectAll();
        return result;
    }

    @Override
    public PageResult<Brand> listBrands(ListBrandsRequest request) {
        // RowBounds rb = new RowBounds(request.getPageIndex(), request.getPageSize());
        // 初始化查询条件，需指定要操作的pojo实体类，此例中操作Book
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(request.getBrandName())) {
            criteria.andLike("name", "%" + request.getBrandName() + "%");
        }
        // criteria.andGreaterThanOrEqualTo("seq", request.getSeq());
        // 分页
        PageHelper.startPage(request.getPageIndex(), request.getPageSize());
        Page<Brand> data = (Page<Brand>) brandMapper.selectByExample(example);
        PageResult<Brand> result = new PageResult<>(data.getTotal(), data.getResult());
        return result;
    }

    @Override
    public Brand previewDetail(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean upsertBrand(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", brand.getName());
        List<Brand> exist = brandMapper.selectByExample(example);
        int result = 0;
        if (exist != null && !exist.isEmpty()) {
            System.out.println("update...");
            brand.setId(exist.stream().findFirst().get().getId());
            result = brandMapper.updateByPrimaryKeySelective(brand);
        } else {
            System.out.println("add...");
            result = brandMapper.insertSelective(brand);
        }
        return result > 0;
    }

    @Override
    public boolean delBrand(Integer id) {
        int count = brandMapper.deleteByPrimaryKey(id);
        return count > 0;
    }

@Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Map> findListByCategoryName(String categoryName) {
       return  brandMapper.findListByCategoryName(categoryName);
    }
}
