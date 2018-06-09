package jdbc;

import java.sql.*;

public class UpdateTest {
	// book 테이블과 author 테이블로부터 데이터 불러오기
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//	1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	2. Connection
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager
					.getConnection(dburl, "bituser", "bituser");
			System.out.println("연결 성공!");
			
			//	3. SQL 준비
			String sql = "UPDATE author SET author_desc=? " + 
			"WHERE author_id=?";
			
			//	4. PreparedStatement 준비
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "헐리우드가 제일 좋아하는 작가");
			stmt.setInt(2, 4);
			
			int updateCount = stmt.executeUpdate();
			
			//	5. 업데이트 결과 수신  및 처리
			System.out.println(updateCount + "개의 레코드가 변경되었습니다.");
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
