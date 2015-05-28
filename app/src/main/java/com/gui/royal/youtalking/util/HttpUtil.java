package com.gui.royal.youtalking.util;

import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.DefaultedHttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**请求网络数据
 * Created by Jeremy on 2015/5/27.
 */
public class HttpUtil {
    /**  HttpRequest方式访问网络
    public static void sendHttpRequest (final String address, final HttpCallbackListener listener) {
        new Thread( new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    //URL url = new URL("http://www.tuling123.com/openapi/api?key=bd35d0e4054c6a4c06059f1a454bd2d3&info=你好");
                    connection = (HttpURLConnection) url.openConnection();
                    //connection.setRequestProperty("Content-Type", "UTF-8");
                    connection.setRequestMethod("GET");//get方式向服务器请求数据
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();//取得返回的输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    String line;
                    StringBuilder response = new StringBuilder();
                    Log.d("请求类型",connection.getContentType());
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d("返回结果", response.toString());
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    */

    /**
     * HttpClient方式访问网络
     */


    public static void sendHttpRequest (final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(address);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Log.d("放回结果",response);
                        if (listener != null) {
                            listener.onFinish(response);
                        }
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }
}
