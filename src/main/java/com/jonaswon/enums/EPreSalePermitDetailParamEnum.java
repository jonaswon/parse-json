package com.jonaswon.enums;

/**
 * 预售许可详情参数枚举
 */
public enum EPreSalePermitDetailParamEnum implements IParamEnum {

    /**
     * 预售许可编号
     */
    DOC_ID("docId", "");

    private String name;
    private String defaultVal;

    EPreSalePermitDetailParamEnum(String name, String defaultVal) {
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
