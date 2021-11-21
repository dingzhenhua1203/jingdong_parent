package com.jingdong.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultMsg<T> implements Serializable {
    private Integer code;
    private T data;
    private String message;

    public ResultMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
