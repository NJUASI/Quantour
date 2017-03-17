package dataHelper.dataHelperImpl;

import java.io.File;

/**
 * Created by Byron Dong on 2017/3/16.
 */
public class Test {

    public String getPath(){
        String path = this.getClass().getResource("/test.txt").getFile();
        return  path;
    }

    public static void main(String []args){

        System.out.println(new Test().getPath());
    }
}
