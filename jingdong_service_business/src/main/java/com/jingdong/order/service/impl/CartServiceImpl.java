package com.jingdong.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jingdong.goods.service.SkuService;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.model.order.CartModel;
import com.jingdong.order.service.CartService;
import com.jingdong.pojo.goods.Sku;
import com.jingdong.util.SpringDataRedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SpringDataRedisHelper redisHelper;

    @Reference
    private SkuService skuService;

    private static final String UserCartHashKey = "h.cart";

    @Override
    public ResultMsg<String> addCart(String userId, String skuId, Integer num) {
        // 1.检查用户是否存在，商品是否过期， 库存是否充足 略
        TypeReference<List<CartModel>> typeReference = new TypeReference<List<CartModel>>() {
        };
        List<CartModel> result = (List<CartModel>) redisHelper.hGet(UserCartHashKey, userId);

        Sku skuItem = skuService.findById(skuId);
        if (CollectionUtils.isEmpty(result)) {
            result = new ArrayList<>();
            CartModel cartModel = new CartModel();
            cartModel.setSkuId(skuId);
            cartModel.setNumber(num);
            cartModel.setIsHit(0);
            cartModel.setSkuItem(skuItem);
            result.add(cartModel);
            redisHelper.hAdd(UserCartHashKey, userId, result);
        } else {
            Optional<CartModel> first = result.stream().filter(u -> u.getSkuId().equals(skuId)).findFirst();
            CartModel cartModel = first.isPresent() ? first.get() : null;
            if (cartModel != null) {
                cartModel.setNumber(cartModel.getNumber() + num);
                redisHelper.hAdd(UserCartHashKey, userId, result);
            } else {
                cartModel = new CartModel();
                cartModel.setSkuId(skuId);
                cartModel.setNumber(num);
                cartModel.setIsHit(0);
                cartModel.setSkuItem(skuItem);
                result.add(cartModel);
                redisHelper.hAdd(UserCartHashKey, userId, result);
            }
        }

        return ResultMsg.SuccessResult("");
    }

    @Override
    public List<CartModel> queryMyCart(String userId) {
        // 1.检查用户是否存在，商品是否过期， 库存是否充足
        List<CartModel> result = (List<CartModel>) redisHelper.hGet(UserCartHashKey, userId);

        return result;
    }

    @Override
    public Boolean updateChecked(String userId, String skuId, boolean checked) {
        return false;
    }

    @Override
    public Integer preferential(String userId) {
        return 0;
    }

    @Override
    public void delCart(String userId, String skuId) {

    }
}
