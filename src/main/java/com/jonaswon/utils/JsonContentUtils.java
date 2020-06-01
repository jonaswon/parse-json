package com.jonaswon.utils;

import com.jonaswon.bean.RequestBean;
import com.jonaswon.enums.ERequestUrlEnum;
import com.jonaswon.service.RequestBeanService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JsonContentUtils {

    /**
     * java-OkHttp
     *
     * @return
     * @throws IOException
     */
    public static String getContentByOkHttp(ERequestUrlEnum requestUrlEnum, Map<String, String> defineMapVal) throws IOException {
        RequestBean requestBean = RequestBeanService.getDetail(requestUrlEnum, defineMapVal);
        // 解析
        String content = okHttpParam(requestBean);

        String url = requestBean.getUrl();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(1000, TimeUnit.SECONDS)//设置读取超时时间
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        return result;
    }

    private static String okHttpParam(RequestBean requestBean) {
        Map<String, String> paramData = requestBean.getParamData();
        String paramName = null;
        String paramVal = null;

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : paramData.entrySet()) {
            paramName = entry.getKey();
            paramVal = entry.getValue();

            stringBuffer.append(paramName).append("=").append(paramVal).append("&");
        }
        String result = stringBuffer.substring(0, stringBuffer.length()-1);
        return result;
    }

    /**
     * java-Unirest
     */
    private String getContentByUnirest(ERequestUrlEnum requestUrlEnum, Map<String, String> defineMapVal) throws UnirestException {
        RequestBean requestBean = RequestBeanService.getDetail(requestUrlEnum, defineMapVal);
        String url = requestBean.getUrl();

        Unirest.setTimeouts(0, 0);
        HttpRequestWithBody httpRequestWithBody = Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        HttpResponse<String> response = unirestParam(httpRequestWithBody, requestBean).asString();
        return response.getBody();
    }

    private MultipartBody unirestParam(HttpRequestWithBody httpRequestWithBody, RequestBean requestBean) {
        Map<String, String> paramData = requestBean.getParamData();
        String paramName = null;
        String paramVal = null;

        MultipartBody multipartBody = null;
        for (Map.Entry<String, String> entry : paramData.entrySet()) {
            paramName = entry.getKey();
            paramVal = entry.getValue();

            if (null == multipartBody) {
                multipartBody = httpRequestWithBody.field(paramName, paramVal);
            } else {
                multipartBody = multipartBody.field(paramName, paramVal);
            }
        }

        return multipartBody;
    }
}
