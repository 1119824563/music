package com.example.music.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    private URL url = null;

    /**
     * @param urlStr
     *            文件所在的网络地址
     * @param path
     *            存储的目录
     * @param fileName
     *            存放的文件名
     * @return 状态
     */
    public String download(String urlStr, String path, String fileName) {
        InputStream inputStream=null;
        try {
            FileUtil fileUtils = new FileUtil();
            //判断文件是否已存在
            if (fileUtils.isFileExist(path + fileName)) {
                return "fileExist";
            } else {
                // 创建一个URL对象
                url = new URL(urlStr);
                // 根据URL对象创建一个HttpURLConnection连接
                HttpURLConnection urlConn = (HttpURLConnection) url
                        .openConnection();
                urlConn.connect();
                // IO流读取数据
                inputStream = urlConn.getInputStream();
                File resultFile = fileUtils.writeToSDCard(path, fileName,
                        inputStream);
                //如果resultFile==null则下载失败
                if (resultFile == null) {
                    return "downloadError";
                }
            }
        } catch (Exception e) {
            //如果异常了，也下载失败
            e.printStackTrace();
            return "downloadError";
        } finally {
            try {
                if(inputStream!=null)
                //别忘了关闭流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "下载完成";

    }
}
