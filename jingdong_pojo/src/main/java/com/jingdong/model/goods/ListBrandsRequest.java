package com.jingdong.model.goods;

import com.jingdong.model.base.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ListBrandsRequest extends BasePageRequest {
    private String brandName;
    private Integer seq;
}
