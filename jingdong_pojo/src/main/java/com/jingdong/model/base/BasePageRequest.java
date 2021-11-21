package com.jingdong.model.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BasePageRequest implements Serializable {
    private int pageIndex;
    private int pageSize;
}
