package dataHelper.dataHelperImpl;

import java.io.BufferedWriter;
import java.io.File;

/**
 * Created by Byron Dong on 2017/3/16.
 */
public class Test {


    public void getPath(){
//        String abPath = Thread.currentThread().getContextClassLoader().getResource("test.txt").getPath();
//        System.out.println(abPath);

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));
        System.out.println(System.getProperty("line.separator"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));

    }

    public static void main(String []args){
        new Test().getPath();
    }
}
