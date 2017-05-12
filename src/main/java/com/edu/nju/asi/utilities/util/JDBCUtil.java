package com.edu.nju.asi.utilities.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtil {

	static Properties pros = null;

	static {//只加载一次
		pros = new Properties();

		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

