package com.edu.nju.asi.task.fileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/6/11.
 */
public class TaskInfoHelper {

    //taskInfo文件路径
    private String taskInfoPath;

    //初始化信息
    public TaskInfoHelper() {
        taskInfoPath = getClass().getClassLoader().getResource("python/taskInfo.txt").getPath();
    }

    /**
     * 写入信息
     *
     * @param name key
     * @param info 内容
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    public void writerInfo(String name, String info) {
        File file = new File(taskInfoPath);
        List<String> allInfo = readInfo();
        allInfo.add(name + "=" + info);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (String line : allInfo) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    public void clearInfo() {
        File file = new File(taskInfoPath);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readInfo() {
        File file = new File(taskInfoPath);
        List<String> info = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                info.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }
}
