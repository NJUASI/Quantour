package com.edu.nju.asi.task.crawler.spider;

import com.edu.nju.asi.utilities.enums.Market;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;

/**
 * Created by Harvey on 2017/5/15.
 */
public class Code_NamePipeline implements Pipeline {

    String rootPath;

    public Code_NamePipeline(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        if(resultItems.get("code_name")!=null){
            addCode_Name((Code_Name) resultItems.get("code_name"));
        }
    }

    public static String ascii2native(String ascii) {

        StringBuilder sb = new StringBuilder();
        String[] strings = ascii.split("\\\\");
        for(int i = 0; i < strings.length; i++){
            if (strings[i].length() < 5){
                sb.append(strings[i].substring(0));
                continue;
            }
            String code = strings[i].substring(1,5);
            char ch = (char) Integer.parseInt(code, 16);
            sb.append(ch);
            if(strings[i].length() > 5){
                sb.append(strings[i].substring(5));
            }
        }

        char c[] = sb.toString().toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }

        return new String(c);
    }

    public void addCode_Name(Code_Name code_name){
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(rootPath+"stockSearch.txt"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.write(code_name.getCode()+";"+Market.valueOf(code_name.getType()).getRepre()+";"
                    + ascii2native(code_name.getName()) + ";" + code_name.getSpell()+"\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
