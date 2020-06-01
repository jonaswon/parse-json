package com.jonaswon.enums;

/**
 * 类型枚举
 */
public enum EUseTypeEnum {
    /**
     * 住宅
     */
    RESIDENCE("住宅", "1"),

    /**
     * 办公
     */
    WORK_OFFICE("办公", "2"),

    /**
     * 商业
     */
    BUSINESS("商业", "3"),

    /**
     * 其它
     */
    OTHER("其它", "4");

    private String name;
    private String val;

    EUseTypeEnum(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public String getVal() {
        return val;
    }
}
