package com.jingdong.pojo.goods;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * para实体类
 * @author Administrator
 *
 */
@Setter
@Getter
@Table(name="tb_para")
public class Para implements Serializable{

	@Id
	private Integer id;//id

	private String name;//名称

	private String options;//选项

	private Integer seq;//排序

	private Integer templateId;//模板ID
}
