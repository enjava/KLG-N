package com.miyin.klg.util;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by en on 2016/12/8.
 */
public class HttpUtil {

    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    /**
     * @param url 请求地址
     * @param cookie cokie
     * @return
     */
    public static String post(String url,String[] cookie) {
           return post(url,null,cookie);
    }

    /**
     * @param url
     * @return
     */
    public static String post(String url) {
        return post(url,null,null);
    }

    /**
     * @param url
     * @param requestParams
     * @param cookie
     * @return
     */
    public static String post(String url, Map<String, String> requestParams, String[] cookie) {
        if (cookie==null||cookie.length<1){
            cookie=new String[1];
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();
        StringBuffer params = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        // 组织请求参数
        if (requestParams != null) {
            Iterator it = requestParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry element = (Map.Entry) it.next();
                params.append(element.getKey());
                params.append("=");
                params.append(element.getValue());
                params.append("&");
            }
        }
        if (params.length() > 0) {
            params.deleteCharAt(params.length() - 1);
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Length", String
                    .valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            if (!TextUtils.isEmpty(cookie[0]))
                httpURLConnection.setRequestProperty("Cookie", cookie[0]);
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write(params.toString());
            // flush输出流的缓冲
            printWriter.flush();
            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);
            } else {
                System.out.println("Post Success!");
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append("/n").append(line);
            }
            String cookieValue = httpURLConnection.getHeaderField("Set-Cookie");
            System.out.println("cookie value:" + cookieValue);
            if (!TextUtils.isEmpty(cookieValue)) {
                cookie[0] = cookieValue.substring(0, cookieValue.indexOf(";"));
                System.out.println("cookie:"+cookie[0]);
            }

            System.out.println(responseResult.toString());
        } catch (Exception e) {
            System.out.println("send post request error!" + e);
        } finally {
            httpURLConnection.disconnect();
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return responseResult.toString();
    }

    /**
     * @param url
     * @return
     */
    public static String get(String url){
        return get(url, null);
    }

    /**
     * @param url
     * @param cookie
     * @return
     */
    public static String get(String url, String[] cookie){
        if (cookie==null||cookie.length<1){
            cookie=new String[1];
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        try {

            URL realUrl = new URL(url.trim());
            //打开连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            if (!TextUtils.isEmpty(cookie[0]))
                httpURLConnection.setRequestProperty("Cookie", cookie[0]);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);//设置超时时间
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);

            } else {
                System.out.println("Post Success!");
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append("/n").append(line);
            }
            String cookieValue = httpURLConnection.getHeaderField("Set-Cookie");
            System.out.println("cookie value:" + cookieValue);
            if (!TextUtils.isEmpty(cookieValue)) {
                cookie[0] = cookieValue.substring(0, cookieValue.indexOf(";"));
                System.out.println("cookie:"+cookie[0]);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpURLConnection.disconnect();
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        return responseResult.toString();
    }

}
