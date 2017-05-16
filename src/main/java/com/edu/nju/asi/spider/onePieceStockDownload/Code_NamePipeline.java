package com.edu.nju.asi.spider.onePieceStockDownload;

import com.edu.nju.asi.spider.Model.Code_Name;
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

    @Override
    public void process(ResultItems resultItems, Task task) {
        if(resultItems.get("code_name")!=null){
            addCode_Name((Code_Name) resultItems.get("code_name"));
        }
    }

    public boolean addCode_Name(Code_Name code_name){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO code_name(code, name, spell, type)" +
                "VALUES(?,?,?,?)";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,code_name.getCode());
            preparedStatement.setString(2,code_name.getName());
            preparedStatement.setString(3,code_name.getSpell());
            preparedStatement.setString(4,code_name.getType());
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
