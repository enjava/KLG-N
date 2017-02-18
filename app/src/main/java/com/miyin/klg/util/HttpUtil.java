package com.miyin.klg.util;

import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by en on 2016/12/8.
 */
public class HttpUtil {

    public static final int SUCEESS = 600;
    public static final int ERROR = 601;
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds

    /**
     * @param url    请求地址
     * @param cookie cokie
     * @return
     */
    public static String post(String url, HttpCookie cookie) {
        return post(url, null, cookie);
    }

    /**
     * @param url           请求地址
     * @param requestParams requestParams
     * @return
     */
    public static String post(String url, Map<String, String> requestParams) {
        return post(url, requestParams, null);
    }

    public static String post(String url, String[] keys, String[] values) {
        if (keys == null || keys.length != values.length)
            return "";
        Map<String, String> requestParams = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            requestParams.put(keys[i], values[i]);
        }

        return post(url, requestParams, null);
    }

    public static String post(String url, String[] keys, String[] values, HttpCookie cookie) {
        if (keys == null || keys.length != values.length)
            return "";
        Map<String, String> requestParams = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            requestParams.put(keys[i], values[i]);
        }

        return post(url, requestParams, cookie);
    }

    /**
     * @param url
     * @param requestParams
     * @param cookie
     * @return
     */
    public static String post(String url, Map<String, String> requestParams, HttpCookie cookie) {
        if (cookie == null) {
            cookie = new HttpCookie();
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
            //httpURLConnection.setRequestProperty("Content-Length", String.valueOf(params.length()));
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            if (!TextUtils.isEmpty(cookie.get()))
                httpURLConnection.setRequestProperty("Cookie", cookie.get());
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
                return responseCode + "";
            } else {
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append("/n").append(line);
            }
            String cookieValue = httpURLConnection.getHeaderField("Set-Cookie");
            if (!TextUtils.isEmpty(cookieValue)) {
                String cookies = cookieValue.substring(0, cookieValue.indexOf(";"));
                cookie.set(cookies);
            }

        } catch (Exception e) {
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

    /**
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * @param url
     * @param cookie
     * @return
     */
    public static String get(String url, HttpCookie cookie) {
        if (cookie == null) {
            cookie = new HttpCookie();
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        try {

            URL realUrl = new URL(url.trim());
            //打开连接
            httpURLConnection = (HttpURLConnection) realUrl.openConnection();
            if (!TextUtils.isEmpty(cookie.get()))
                httpURLConnection.setRequestProperty("Cookie", cookie.get());
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);//设置超时时间
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {

            } else {
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseResult.append("/n").append(line);
            }
            String cookieValue = httpURLConnection.getHeaderField("Set-Cookie");
            if (!TextUtils.isEmpty(cookieValue)) {
                String cookies = cookieValue.substring(0, cookieValue.indexOf(";"));
                cookie.set(cookies);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public static String uploadFormFile(String urlStr, String key, String fileName, InputStream inputStream, HttpCookie cookie) {
        Map<String, String> map = new HashMap<>();
        map.put(key, fileName);
        InputStream[] inputStreams = new InputStream[]{inputStream};
        return uploadFormFile(urlStr, map, inputStreams, cookie);
    }

    ;

    public static String uploadFormFile(String urlStr, Map<String, String> fileMap, InputStream[] inputStream, HttpCookie cookie) {

        if (cookie == null) {
            cookie = new HttpCookie();
        }
        String result = "";
        HttpURLConnection conn = null;
        //分隔符
        String finalSplit = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + finalSplit);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (!TextUtils.isEmpty(cookie.get()))
                conn.setRequestProperty("Cookie", cookie.get());
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 上传文件
            if (fileMap != null) {
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
                int i = 0;
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = entry.getKey();
                    String inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
//                    MagicMatch match = Magic.getMagicMatch(file, false, true);
//                    String filecontentType = match.getMimeType();
                    String contentType = inputValue.substring(inputValue.lastIndexOf(".") + 1);
                    //file.ge
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(finalSplit).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    DataInputStream in = new DataInputStream(inputStream[i]);
                    i++;
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }
            byte[] endData = ("\r\n--" + finalSplit + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            // 读取返回数据
            StringBuffer strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            result = strBuf.toString();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }


    /**
     * @param urlPath     下载路径
     * @param downloadDir 下载存放目录
     * @return 返回下载文件
     */
    public static File downloadFile(String urlPath, String downloadDir) {
        File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();

            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            URLConnection con = url.openConnection();

            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileFullName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 打印下载百分比
                // System.out.println("下载了-------> " + len * 100 / fileLength +
                // "%\n");
            }
            bin.close();
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return file;
        }

    }

    public static class HttpCookie {
        private String cookie;

        public String get() {
            return cookie;
        }

        public void set(String cookie) {
            this.cookie = cookie;
        }

        @Override
        public String toString() {
            return cookie;
        }

        @Override
        public boolean equals(Object o) {

            if (o == null || getClass() != o.getClass())
                return false;
            HttpCookie that = (HttpCookie) o;
            return cookie.equals(that.cookie);

        }

        @Override
        public int hashCode() {
            return cookie.hashCode();
        }
    }


}
