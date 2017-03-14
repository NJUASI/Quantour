package utilities;

import com.github.stuxuhai.jpinyin.*;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by Harvey on 2017/3/14.
 *
 * 获取股票名称拼音首字母的工具类
 */
public class ShortPinyinUtil {



    /**
     * Convert to short pinyin. 获取股票名称的拼音首字母,并以首字母——股票名键值对的形式存入文件
     *
     * @param stockNames the stock names 全部的股票名称
     */
    public static void convertToShortPinyin(List<String> stockNames){

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("shortPinyin.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder sb = null;
        for (String name : stockNames) {
            sb = new StringBuilder();
            char letter = 0;
            for(int i = 0;i < name.length() ; i++){
                //如果是汉字，就获取首字母
                if(ChineseHelper.isChinese(name.charAt(i))){
                    try {
                        letter = PinyinHelper.getShortPinyin(String.valueOf(name.charAt(i))).charAt(0);
                    } catch (PinyinException e) {
                        e.printStackTrace();
                    }
                }
                //如果不是汉字，直接赋值
                else{
                    letter = name.charAt(i);
                }
                //将首字母全部转换为小写
                sb.append(Character.toLowerCase(letter));
            }
            properties.put(sb.toString(),name);
        }
    }
}
