package com.edu.nju.asi.spider.onePieceStockDownload;

import com.edu.nju.asi.spider.Model.Code_Name;
import com.edu.nju.asi.utilities.enums.Market;
import com.edu.nju.asi.utilities.util.JDBCUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Harvey on 2017/5/15.
 */
public class Code_NamePipeline implements Pipeline {

    public static void main(String[] args) {
//        System.out.println(ascii2native("\u4e07  \u79d1\uff21"));

        String ascii = "\\u4e07  \\u79d1\\uff21";
        System.out.println(ascii2native(ascii));
//        int i = ascii.indexOf('\\');
//        System.out.println(ascii.indexOf("\\"));
//        System.out.println(ascii.indexOf("\\"));
//        System.out.println(ascii.indexOf("\\"));
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        if(resultItems.get("code_name")!=null){
            addCode_Name((Code_Name) resultItems.get("code_name"));
        }
    }

    public static String ascii2native(String ascii) {
//        ascii.replaceAll(" ","");

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

    public boolean addCode_Name(Code_Name code_name){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO stocksearch(code, market, name, firstLetters)" +
                "VALUES(?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,code_name.getCode());
            preparedStatement.setInt(2,Market.valueOf(code_name.getType()).getRepre());
            System.out.println(code_name.getName());
            preparedStatement.setString(3,ascii2native(code_name.getName()));
            preparedStatement.setString(4,code_name.getSpell());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("名称写入成功");
            System.out.println("-----------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        }finally {
            JDBCUtil.close(preparedStatement,connection);
        }

        return result;
    }
}
