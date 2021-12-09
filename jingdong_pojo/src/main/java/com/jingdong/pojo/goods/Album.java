package com.jingdong.pojo.goods;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * album实体类
 * @author Administrator
 *
 */
@Setter
@Getter
@Table(name="tb_album")
public class Album implements Serializable{

	@Id
	private Long id;//编号

	private String title;//相册名称

	private String image;//相册封面

	private String imageItems;//图片列表
}
