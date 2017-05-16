package com.edu.nju.asi.spider.allStocksDownload;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.edu.nju.asi.spider.DownloadDataHelper;
import org.apache.http.annotation.ThreadSafe;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

@ThreadSafe
public class StocksDownloadPipeline extends FilePersistentBase implements Pipeline {

    private DownloadDataHelper dataHelper = new DownloadDataHelper();

    @Override
    public void process(ResultItems resultItems, Task task) {
        String targetUrl = resultItems.get("url");
        String code = resultItems.get("code");

        //重试直到成功
        if (targetUrl != null) {
            boolean isSuccess = false;
            while (!isSuccess){
                try {
                    downLoadFromUrl(targetUrl, "G:/Quantour/stocks", code);
                    isSuccess = true;
                    //转储到数据库
                    dataHelper.normalStockStore("G:"+File.separator+"Quantour"+File.separator+"stocks"+File.separator+code+".csv");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        conn.setConnectTimeout(600000);

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
