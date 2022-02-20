package com.jingdong.order.service;

import com.jingdong.model.base.ResultMsg;
import com.jingdong.model.order.CartModel;

import java.util.*;
/**
 * 购物车服务
 */
public interface CartService {

    /**
     * 添加购物车
     * @param userId
     * @param skuId
     * @param num
     * @return
     */
    public ResultMsg<String> addCart(String userId,String skuId,Integer num);

    /**
     * 从redis中提取购物车
     * @param userid
     * @return
     */
    public List<CartModel> queryMyCart(String userid);


    /**
     * 更新选中状态
     * @param userId
     * @param skuId
     * @param checked
     */
    public Boolean updateChecked(String userId, String skuId,boolean checked);


    /**
     * 计算当前选中的购物车的优惠金额
     * @param
     * @return
     */
    public Integer preferential(String userId);


    /**
     * 删除选中的购物车
     * @param userId
     */
    public void delCart(String userId,String skuId);

}
