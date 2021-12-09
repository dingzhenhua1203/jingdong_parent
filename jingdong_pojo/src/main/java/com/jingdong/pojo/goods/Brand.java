package com.jingdong.pojo.goods;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "tb_brand")
public class Brand implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String image;
    private String letter;
    private Integer seq;
}
