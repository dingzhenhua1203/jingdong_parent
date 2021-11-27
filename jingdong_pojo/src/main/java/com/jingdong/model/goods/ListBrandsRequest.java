package com.jingdong.model.goods;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jingdong.model.base.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListBrandsRequest extends BasePageRequest {
    private String brandName;
    private Integer seq;
}
