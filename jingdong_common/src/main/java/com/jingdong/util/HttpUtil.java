package com.jingdong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author kevin
 * @since 2020/2/27 12:20 PM
 */
public class HttpUtil {

    public static String post(String url, String body){
        String result = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {

                result =  EntityUtils.toString(responseEntity);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static String get(String url) {
        String result = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {

                result =  EntityUtils.toString(responseEntity);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
