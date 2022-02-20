package com.jingdong.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jingdong.model.base.ResultMsg;
import com.jingdong.model.order.CartModel;
import com.jingdong.order.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * requestmapping中的地址大小写敏感
 */
@RequestMapping("/cart")
@RestController
public class CartController {

    @Reference
    private CartService cartService;

    @GetMapping("/queryMyCart/{userId}")
    public List<CartModel> queryMyCart(@PathVariable("userId") String userId) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!userId.equals(name)) {
            return new ArrayList<>();
        }
        return cartService.queryMyCart(name);
    }

    @GetMapping("/addCart/{userId}")
    public ResultMsg<String> addCart(@PathVariable("userId") String userId, String skuId, Integer num) {
        return cartService.addCart(userId, skuId, num);
    }
}

