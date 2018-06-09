package jdbc;

import java.sql.*;

public class ConnectionTest {

	public static void main(String[] args) {

		try {
			//	1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	2. Connection
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			Connection conn = DriverManager
					.getConnection(dburl, "bituser", "bituser");
			System.out.println("연결 성공!");
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버를 찾지 못했어요.");
		} catch(SQLException e) {
			System.err.println("Error:" + e.getMessage());
		}

		
	}

}
