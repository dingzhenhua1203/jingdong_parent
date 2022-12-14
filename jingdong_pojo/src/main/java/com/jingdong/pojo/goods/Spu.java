package com.jingdong.pojo.goods;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * spu实体类
 * @author Administrator
 *
 */
@Setter
@Getter
@Table(name="tb_spu")
public class Spu implements Serializable{

	@Id
	private String id;//主键

	private String sn;//货号

	private String name;//SPU名

	private String caption;//副标题

	private Integer brandId;//品牌ID

	private Integer category1Id;//一级分类

	private Integer category2Id;//二级分类

	private Integer category3Id;//三级分类

	private Integer templateId;//模板ID

	private Integer freightId;//运费模板id

	private String image;//图片

	private String images;//图片列表

	private String saleService;//售后服务

	private String introduction;//介绍

	private String specItems;//规格列表

	private String paraItems;//参数列表

	private Integer saleNum;//销量

	private Integer commentNum;//评论数

	private String isMarketable;//是否上架

	private String isEnableSpec;//是否启用规格

	private String isDelete;//是否删除

	private String status;//审核状态
	
}
