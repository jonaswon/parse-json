package com.jonaswon.enums;

/**
 * 详情参数枚举
 */
public enum EDetailParamEnum implements IParamEnum {

    DISTRICT("district", "晋江市灵源街道"),
    USETYPE("usetype", EUseTypeEnum.RESIDENCE.getVal()),
    REGISTTIME1("registtime1", "1990-01-01"),
    REGISTTIME2("registtime2", "2020-05-29"),
    START("start", "0"),
    SIZE("size", "100000");

    private String name;
    private String defaultVal;

    EDetailParamEnum(String name, String defaultVal) {
        this.name = name;
        this.defaultVal = defaultVal;
    }

    public String getName() {
        return name;
    }

    public String getDefaultVal() {
        return defaultVal;
    }
}
