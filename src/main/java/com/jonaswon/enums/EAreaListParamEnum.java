package com.jonaswon.enums;

public enum EAreaListParamEnum implements IParamEnum {

    ID("id", "1"),
    HOUSE_TYPE("houseType", "1");

    private String name;
    private String defaultVal;

    EAreaListParamEnum(String name, String defaultVal) {
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
