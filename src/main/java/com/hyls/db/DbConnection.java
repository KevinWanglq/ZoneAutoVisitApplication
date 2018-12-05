package com.hyls.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hyls.model.User;


public class DbConnection {

	private static Connection conn = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/jfinal_demo?useSSL=true&useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true";
	private static String username = "root";
	private static String password = "mystar?92";
	
	public static void exeUpdSql(String sql,Connection conn) {
		 PreparedStatement pstmt = null;
		 try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<User> getUserList(String sql,Connection conn) {
		 PreparedStatement pstmt = null;
		 List<User> list = new ArrayList<User>();
		 try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			User user =null;
			while(rs.next()) {
				user = new User();
				user.setName(rs.getString("login_name"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			releasePrep(pstmt);
			releaseConn(conn);
		}
		return list;
	}
	
	public static Connection getConn() {
	    try {
	      if(conn == null) {
	        Class.forName(driver); 
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	      }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	
	private static void releaseConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void releasePrep(PreparedStatement ps) {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
