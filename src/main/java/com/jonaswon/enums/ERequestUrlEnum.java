package com.jonaswon.enums;

public enum ERequestUrlEnum {

    /**
     * 晋江的地区列表api
     */
    AREA_LIST("地区列表", "http://120.33.31.10:2019/qzjsj_web2/tjsj/list.do", EAreaListParamEnum.values()),

    /**
     * 地区（住宅、办公、商业、其它）的销售详情api
     */
    DETAIL("地区楼盘销售情况详情", "http://120.33.31.10:2019/qzjsj_web2/qxDetail/list.do", EDetailParamEnum.values());

    private String name;
    private String url;
    private IParamEnum[] paramEnums;

    ERequestUrlEnum(String name, String url, IParamEnum[] paramEnums) {
        this.name = name;
        this.url = url;
        this.paramEnums = paramEnums;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public IParamEnum[] getParamEnums() {
        return paramEnums;
    }
}
