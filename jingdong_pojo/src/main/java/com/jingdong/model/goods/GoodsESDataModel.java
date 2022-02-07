package com.jingdong.model.goods;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class GoodsESDataModel implements Serializable {
    public String goodsId;
    public String goodsName;
    public String goodsImg;
    public Integer price;
    public Date createDate;
    public String categoryName;
    public String brandName;
    public String spec;
    public Integer stocksNumber;
    public Integer salesNumber;
    public Integer commentNumber;
}
