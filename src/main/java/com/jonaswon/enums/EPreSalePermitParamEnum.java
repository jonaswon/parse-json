package com.jonaswon.enums;

/**
 * 预售许可参数枚举
 */
public enum EPreSalePermitParamEnum implements IParamEnum {

    /**
     * 项目名称
     */
    ITEM_NAME("itemname", ""),
    /**
     * 开发商
     */
    COMP_NAME("compname", ""),
    /**
     * 预售许可号
     */
    PRESALE_NO("presaleNo", ""),
    START("start", "0"),
    SIZE("size", "100000");

    private String name;
    private String defaultVal;

    EPreSalePermitParamEnum(String name, String defaultVal) {
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
