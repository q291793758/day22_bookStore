package cn.itcast.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcUtils_bak {
	
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSource ds;

	static{
		ds = new ComboPooledDataSource("bak");

	}
	
	public static DataSource getDataSource(){
		return ds;
	}

	public static Connection getConnection(){
		try{
			Connection conn = tl.get();
			if(conn==null){
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			}
			tl.set(conn);
			return conn;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void StartTransaction(){
		try{
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void commitTransaction(){
		try{
			Connection conn = getConnection();
			if(conn!=null){
				conn.commit();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeConn(){
		Connection conn = null;
		try{
			conn = getConnection();
			if(conn!=null){
				conn.close();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tl.remove();
		}
	}
}
