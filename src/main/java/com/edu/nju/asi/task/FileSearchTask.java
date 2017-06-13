package com.edu.nju.asi.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by cuihua on 2017/6/9.
 */
public class FileSearchTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String path = "/Users/cuihua/desktop";
        String keyword = "已";
        int c = 0;
        File[] files = new File(path).listFiles();
        ArrayList<Future<Integer>> rs = new ArrayList<>();
        for(File file: files){  //每个文件启动一个task去查找
            MatchCount count = new MatchCount();
            count.file = file;
            count.keyword = keyword;
            FutureTask<Integer> task = new FutureTask(count);
            rs.add(task); //将任务返回的结果添加到集合中
            Thread thread = new Thread(task);
            thread.start();
        }

        for(Future<Integer> f: rs){
            c += f.get(); //迭代返回结果并累加
        }
        System.out.println("包含关键字的总文件数为：" + c);
    }
}

class  MatchCount implements Callable<Integer> {
    public File file;
    public String keyword;
    private  Integer count = 0;

    public Integer call() throws Exception {   //call封装线程所需做的任务
        if(search(file)){
            count ++;
            System.out.println("IN file: " + file);
        }
        return count;
    }

    public boolean search(File file){
        boolean founded = false;
        try(Scanner scanner = new Scanner(new FileInputStream(file))){
            while(!founded && scanner.hasNextLine()){
                if (scanner.nextLine().contains(keyword))
                    founded = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return founded;
    }
}