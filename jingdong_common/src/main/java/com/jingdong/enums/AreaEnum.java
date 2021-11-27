package com.jingdong.enums;

/**
 * Created by Andrew.Li on 2019/2/19
 */
public enum AreaEnum {
    PROVINCE("省", "1"),
    CITY("市", "2"),
    REGIO("区", "3"),
    COUNTY("县", "4"),
    PLATE("板块", "5");

    private String name;
    private String code;


    private AreaEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(String code) {
        for (AreaEnum c : AreaEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public static AreaEnum getByCode(String value) {
        for (AreaEnum code : values()) {
            if (code.getCode().equals(value)) {
                return code;
            }
        }
        return null;
    }
}
