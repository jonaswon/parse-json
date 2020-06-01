package com.jonaswon.service;

import com.jonaswon.bean.RequestBean;
import com.jonaswon.enums.ERequestUrlEnum;
import com.jonaswon.enums.IParamEnum;

import java.util.HashMap;
import java.util.Map;

public class RequestBeanService {

    public static RequestBean getDetail(ERequestUrlEnum requestUrlEnum, Map<String, String> defineMapVal) {
        RequestBean requestBean = new RequestBean();

        String url = "";
        Map<String, String> paramData = new HashMap<>();

        url = requestUrlEnum.getUrl();
        IParamEnum[] paramEnums = requestUrlEnum.getParamEnums();
        String paramName = null;
        String paramVal = null;

        for (IParamEnum curParam : paramEnums) {
            paramName = curParam.getName();

            if (null != defineMapVal && !defineMapVal.isEmpty() && defineMapVal.containsKey(paramName)) {
                paramVal = defineMapVal.get(paramName);
            } else {
                paramVal = curParam.getDefaultVal();
            }
            paramData.put(paramName, paramVal);
        }

        requestBean.setUrl(url);
        requestBean.setParamData(paramData);

        return requestBean;
    }
}
