package com.jingdong.model.order;

import com.jingdong.pojo.goods.Sku;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CartModel implements Serializable {
    private String skuId;
    private int isHit;
    private int skuStatus;
    private int number;
    private Sku skuItem;
}
