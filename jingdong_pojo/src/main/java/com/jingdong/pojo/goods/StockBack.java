package com.jingdong.pojo.goods;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Setter
@Getter
@Table(name = "tb_stock_back")
public class StockBack {

    @Id
    private String orderId;

    @Id
    private String skuId;

    private Integer num;

    private String status;

    private Date createTime;

    private Date backTime;
}
