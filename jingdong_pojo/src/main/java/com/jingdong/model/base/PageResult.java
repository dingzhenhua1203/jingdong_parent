package com.jingdong.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private long totals;
    private List<T> results;
}
