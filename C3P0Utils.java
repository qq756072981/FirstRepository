import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	//创建一个c3p0的DataSource(配置文件的方式)
	static DataSource cpds = new ComboPooledDataSource();
	
	//返回数据源
	public static DataSource getDataSource(){
		return cpds;
	}
	
	
	//获得连接
	public static Connection getConnection() throws SQLException{
		Connection connection = cpds.getConnection();
		return connection;
	}
	
	//关闭资源  
	public static void release(ResultSet resultSet,Statement statement,Connection connection){
		
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
