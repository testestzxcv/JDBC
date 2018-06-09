package jdbc;

import java.sql.*;

public class SelectTest {
	// book 테이블과 author 테이블로부터 데이터 불러오기
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//	1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	2. Connection
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager
					.getConnection(dburl, "bituser", "bituser");
			System.out.println("연결 성공!");

			//	3. Statement 준비
			stmt = conn.createStatement();
			//	4. SQL
			String sql = "SELECT book_id, title, author_name " + 
					"FROM book b, author a " +
					"WHERE b.author_id = a.author_id";
			rs = stmt.executeQuery(sql);
			//	5. Fetch row (row를 하나씩 가져오기)
			while (rs.next()) {
				String bookId = rs.getString(1);
				String title = rs.getString(2);
				String authorName = rs.getString(3);
				
				System.out.println(bookId+ ":" + title + "("+authorName + ")");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버를 찾지 못했어요.");
		} catch(SQLException e) {
			System.err.println("Error:" + e.getMessage());
		} finally { // 리소스 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				
			}
		}

	}

}
