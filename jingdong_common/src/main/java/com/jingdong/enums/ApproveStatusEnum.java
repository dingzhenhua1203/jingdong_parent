package com.jingdong.enums;

public enum ApproveStatusEnum {

    DELETE("删除", -1),
    DRAFT("草稿", 1),
    APPROVING("审批中", 3),
    APPROVED("审批通过", 4),
    APPROVE_FAILED("审批不通过", 5),
    DRAFT_DENY("审批拒绝", 10),
    REVOKE("撤回", 20);

    private String name;
    private int code;

    ApproveStatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (ApproveStatusEnum c : ApproveStatusEnum.values()) {
            if (c.getCode() == code) {
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
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

}
