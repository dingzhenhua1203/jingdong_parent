package com.jingdong.goods.service;

import com.jingdong.model.base.PageResult;
import com.jingdong.model.goods.GoodsDto;
import com.jingdong.pojo.goods.Spu;

import java.util.*;

/**
 * 业务逻辑层
 */
public interface SpuService {


    public List<Spu> findAll();


    public PageResult<Spu> findPage(int page, int size);


    public List<Spu> findList(Map<String,Object> searchMap);


    public PageResult<Spu> findPage(Map<String,Object> searchMap,int page, int size);


    public Spu findById(String id);

    public void add(Spu spu);


    public void update(Spu spu);


    public void delete(String id);

    /**
     * 逻辑删除
     * @param id
     */
    public void logicDelete(String id);

    /**
     * 恢复数据
     * @param id
     */
    public void restore(String id);

    /**
     * 审核商品
     * @param id
     */
    public void audit(String id);

    /**
     * 下架商品
     * @param id
     */
    public void pull(String id);

    /**
     * 上架商品
     * @param id
     */
    public void put(String id);

    /**
     * 批量上架商品
     * @param ids
     */
    public int putMany(String[] ids);

    /**
     *  保存商品
     * @param goods 商品组合实体类
     */
    public void saveGoods(GoodsDto goods);


    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    public GoodsDto findGoodsById(String id);

}
