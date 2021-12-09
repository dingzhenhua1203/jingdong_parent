package com.jingdong.pojo.goods;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * spec实体类
 * @author Administrator
 *
 */
@Setter
@Getter
@Table(name="tb_spec")
public class Spec implements Serializable{

	@Id
	private Integer id;//ID

	private String name;//名称

	private String options;//规格选项

	private Integer seq;//排序

	private Integer templateId;//模板ID


}
