package com.jonaswon.enums;

/**
 * 预售许可详情参数枚举
 */
public enum EPreSalePermitHouseParamEnum implements IParamEnum {

    /**
     * 预售许可编号
     */
    PRESALE_NO("presaleNo", "");

    private String name;
    private String defaultVal;

    EPreSalePermitHouseParamEnum(String name, String defaultVal) {
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
