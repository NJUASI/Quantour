package com.edu.nju.asi.spider.onePieceStockDownload;

import com.edu.nju.asi.spider.DownloadDataHelper;
import org.assertj.core.internal.cglib.core.Local;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/5/15.
 *
 * 通过下载链接下载所有股票历史数据,起始日从2007年1月1日起
 */
public class StockDownloader {

    //下载三只基准股票全部的数据
    public void downLoadBaseStock(String savePath, LocalDate startDay, LocalDate today){

        List<String> baseCodes = new ArrayList<>();

        baseCodes.add("0000001");
        baseCodes.add("1399001");
        baseCodes.add("0000300");
        baseCodes.add("1399005");
        baseCodes.add("1399006");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        String prefix = "http://quotes.money.163.com/service/chddata.html?";
        String suffix = "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";

        String start = startDay.format(formatter);
        String end = today.format(formatter);

        boolean isSuccess1 = false;
        boolean isSuccess2 = false;
        boolean isSuccess3 = false;
        boolean isSuccess4 = false;
        boolean isSuccess5 = false;

        String url = prefix+"code="+baseCodes.get(0)+"&start="+start+"&end="+end+suffix;
        while(!isSuccess1){
            try {
                downLoadFromUrl(url,savePath,baseCodes.get(0));
                isSuccess1 = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = prefix+"code="+baseCodes.get(1)+"&start="+start+"&end="+end+suffix;
        while(!isSuccess2){
            try {
                downLoadFromUrl(url,savePath,baseCodes.get(1));
                isSuccess2 = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = prefix+"code="+baseCodes.get(2)+"&start="+start+"&end="+end+suffix;
        while(!isSuccess3){
            try {
                downLoadFromUrl(url,savePath,baseCodes.get(2));
                isSuccess3 = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = prefix+"code="+baseCodes.get(3)+"&start="+start+"&end="+end+suffix;
        while(!isSuccess4){
            try {
                downLoadFromUrl(url,savePath,baseCodes.get(3));
                isSuccess4 = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        url = prefix+"code="+baseCodes.get(4)+"&start="+start+"&end="+end+suffix;
        while(!isSuccess5){
            try {
                downLoadFromUrl(url,savePath,baseCodes.get(4));
                isSuccess5 = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param savePath
     * @throws IOException
     */
    public void  downLoadFromUrl(String urlStr,String savePath, String code) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoInput(true);
        conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.96 Safari/537.36");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        //设置超时间为3秒
        conn.setConnectTimeout(60000);

        System.out.println(conn.getResponseMessage());

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        File file = new File(saveDir+File.separator+code+".csv");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        System.out.println("info:"+url+" download success");

    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
