package com.itheima.jdbc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
	//硬编码，就是将一些常量写死在类中。将常量放在配置文件中，哪里需要用了，就直接从配置文件中读取
	//有两种文件类型可以用来做配置文件第一种是properties文件，第二种是xml文件
	static String url;
	static String user;
	static String password;
	static String driverClass;
	static{
		try {
			//从配置文件中读取url、user、password
			//读取配置文件
			Properties properties = new Properties();
			//使用properties对象，将properties文件中的数据加载进来
			InputStream inStream = new FileInputStream(new File("src/jdbcconfig.properties"));
			properties.load(inStream);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			driverClass = properties.getProperty("driverClass");
			inStream.close();
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得连接的方法
	 * @return
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static Connection getConnection() throws SQLException, IOException{
		//2.获得链接
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	/**
	 * 释放资源
	 * @param resultSet
	 * @param statement
	 * @param connection
	 * @throws SQLException
	 */
	public static void release(ResultSet resultSet,Statement statement,Connection connection) throws SQLException{
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
