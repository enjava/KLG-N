package com.miyin.klg.util;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by en on 2017/2/17.
 */

public class Http {

    private volatile static Http singleton;

    private final int CONNECT_TIMEOUT = 5000; // in milliseconds

    private Http() {
    }

    public static Http instance() {
        if (singleton == null) {
            synchronized (Http.class) {
                if (singleton == null) {
                    singleton = new Http();
                }
            }
        }
        return singleton;
    }

    public static class HttpSession {
        private String session;

        public String get() {
            return session;
        }

        public void set(String session) {
            this.session = session;
        }
    }

    /**
     * @param url
     * @param requestParams
     * @param session
     * @return
     */
    public String post(String url, Map<String, String> requestParams, HttpSession session) {

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
            System.out.println(params);
            params.deleteCharAt(params.length() - 1);
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            //httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            if (!TextUtils.isEmpty(session.get()))
                httpURLConnection.setRequestProperty("Cookie", session.get());
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送
            // 请求参数
            printWriter.write(params.toString());
            // flush输出流的缓冲
            printWriter.flush();
            // 根据ResponseCode判断连接是否成功
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                System.out.println(" Error===" + responseCode);
                return responseCode + "";
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
                String cookies = cookieValue.substring(0, cookieValue.indexOf(";"));
                System.out.println("cookie:" + cookies);
                session.set(cookies);
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

        return responseResult.toString().replace("/n", "").trim();
    }

    public static class HttpData {

        private volatile static HttpData singleton;

        private Map<String, String> map = new ConcurrentHashMap();

        private HttpData() {
        }

        public static HttpData instance() {
            if (singleton == null) {
                synchronized (HttpData.class) {
                    if (singleton == null) {
                        singleton = new HttpData();
                    }
                }
            }
            return singleton;
        }

        public void put(String key, String value) {
            map.put(key, value);
        }

        protected Map<String, String> get() {
            return map;
        }
    }

    private OnHttpListener httpListener;
    private static final int HTTP_POST = 300;
    private HttpSession cookie;
    private String postJson;

    public synchronized void post(@NonNull final String postUrl, final HttpSession session, @NonNull OnHttpListener listener) {
        httpListener = listener;
        final HttpData dataMap = HttpData.instance();
        listener.setData(dataMap);
        new Thread() {
            @Override
            public void run() {
                cookie = session;
                if (cookie == null)
                    cookie = new HttpSession();
                Map<String, String> map = dataMap.get();
                postJson = post(postUrl, map, cookie);
                Message msg = Message.obtain();
                msg.what = HTTP_POST;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_POST:
                    if (TextUtils.isEmpty(postJson))
                        httpListener.onNonConcect();
                    else if (postJson.indexOf("操作成功") != -1)
                        httpListener.onSuccess(cookie, postJson);
                    else if (postJson.indexOf("请先登录") != -1)
                        httpListener.onLogout();
                    else {
                        httpListener.onOther(postJson);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public interface OnHttpListener {
        //传递网络参数
        void setData(HttpData httpData);

        //成功
        void onSuccess(HttpSession session, String json);

        //未登录
        void onLogout();

        //网络异常
        void onNonConcect();

        void onOther(String json);
    }

    public abstract class OnHttpListenerProxy implements OnHttpListener {
        public void onNonConcect() {
            onFail("");
        }

        public void OnOther(String json) {
            onFail(json);
        }

        public void onLogout(String json) {
            onFail(json);
        }

        abstract void onFail(String json);
    }

}
