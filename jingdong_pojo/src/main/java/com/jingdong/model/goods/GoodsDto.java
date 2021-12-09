package com.jingdong.model.goods;

import com.jingdong.pojo.goods.Sku;
import com.jingdong.pojo.goods.Spu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class GoodsDto implements Serializable {

    private Spu spu;

    private List<Sku> skuList;
}
