package com.edu.nju.asi.utilities.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Byron Dong on 2017/4/11.
 */

public class JDBCUtil {

	static Properties pros = null;

	//只加载一次
	static {
		pros = new Properties();

		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * 获取sql链接
	 *
	 * @auther Byron Dong
	 * @lastUpdatedBy Byron Dong
	 * @updateTime 2017/4/11
	 * @return Connection sql链接
	 */
	public static Connection getConnection(){
		try {
			Class.forName(pros.getProperty("jdbc.driver"));
			return DriverManager.getConnection(pros.getProperty("jdbc.url"),
					pros.getProperty("jdbc.username"),pros.getProperty("jdbc.password"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *
	 * 关闭sql链接
	 *
	 * @auther Byron Dong
	 * @lastUpdatedBy Byron Dong
	 * @updateTime 2017/4/11
	 * @param ps sql执行Statement
	 * @param  conn 链接
	 */
	public static void close(PreparedStatement ps, Connection conn){
		try{
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		try{
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}

