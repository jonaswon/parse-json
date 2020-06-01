package com.jonaswon.bean;

import java.util.Map;

public class RequestBean {
    private String url;
    private Map<String, String> paramData;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParamData() {
        return paramData;
    }

    public void setParamData(Map<String, String> paramData) {
        this.paramData = paramData;
    }
}
