package com.jonaswon.enums;

public enum ERequestUrlEnum {

    /**
     * 晋江的地区列表api
     */
    AREA_LIST("地区列表", "http://120.33.31.10:2019/qzjsj_web2/tjsj/list.do", EAreaListParamEnum.values()),

    /**
     * 地区（住宅、办公、商业、其它）的销售详情api
     */
    DETAIL("地区楼盘销售情况详情", "http://120.33.31.10:2019/qzjsj_web2/qxDetail/list.do", EDetailParamEnum.values()),

    /**
     * 预售许可api
     */
    PRE_SALE_PERMIT("预售许可", "http://120.33.31.10:2019/qzjsj_web2/presalePermit/list.do", EPreSalePermitParamEnum.values()),

    /**
     * 预售许可详情api
     */
    PRE_SALE_PERMIT_DETAIL("预售许可详情", "http://120.33.31.10:2019/qzjsj_web2/presalePermit/list_detail.do", EPreSalePermitDetailParamEnum.values()),

    /**
     * 预售许可详情楼幢列表api
     */
    PRE_SALE_PERMIT_HOUSE("预售许可详情楼幢列表", "http://120.33.31.10:2019/qzjsj_web2/zsBuidings/list_lpxx_permitno.do", EPreSalePermitDetailParamEnum.values());

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
