package com.edu.nju.asi.task.fileHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/6/11.
 */
public class TaskInfoHelper {

    //taskInfo的peoperties对象
    private Properties taskInfoHelper;

    //taskInfo文件路径
    private String taskInfoPath;

    //初始化信息
    public TaskInfoHelper() {
        taskInfoPath = getClass().getClassLoader().getResource("python/taskInfo.properties").getPath();
        loadInfo();
    }

    /**
     * 写入信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @param name key
     * @param info 内容
     * @updateTime 2017/6/11
     */
    public void writerInfo(String name, String info) {
        taskInfoHelper.put(name,info);
        storeInfo();
    }

    /**
     * 清空信息
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    public void clearInfo() {
        taskInfoHelper.clear();
        storeInfo();
    }

    /**
     * 加载properties对象
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    private void loadInfo() {
        try {
            FileInputStream fileInputStream = new FileInputStream(taskInfoPath);
            taskInfoHelper.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储内容
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/11
     */
    private void storeInfo() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(taskInfoPath);
            taskInfoHelper.store(fileOutputStream,"commit");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
