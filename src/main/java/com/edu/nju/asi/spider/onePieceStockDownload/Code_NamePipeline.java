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

        String ascii = "\u4e07  \u79d1\uff21";
        int i = ascii.indexOf('\\');
        System.out.println(i);
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
        String needTobeConvert = ascii.substring(ascii.indexOf('\\'));
        int n = needTobeConvert.length() / 6;
        StringBuilder sb = new StringBuilder();
        sb.append(ascii.substring(0,ascii.indexOf('\\')));
        for (int i = 0, j = 2; i < n; i++, j += 6) {
            String code = needTobeConvert.substring(j, j + 4);
            char ch = (char) Integer.parseInt(code, 16);
            sb.append(ch);
        }
        return sb.toString();
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
            preparedStatement.setString(3,ascii2native(code_name.getName()));
            System.out.println(ascii2native(code_name.getName()));
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
