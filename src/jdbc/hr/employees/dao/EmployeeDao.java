package jdbc.hr.employees.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.hr.employees.vo.EmployeeVo;

public class EmployeeDao implements IEmployeeDao{
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			//	드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//	데이터베이스에 접속
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "hr", "hr");
		}catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 찾을 수 없습니다.");
		}
		
		return conn;

	}

	@Override
	public List<EmployeeVo> getList() {
		// 전체 직원 정보 반환
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<EmployeeVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			//	쿼리
			String sql = "SELECT employee_id, first_name, " +
					"last_name, salary FROM employees";
			//	쿼리 수행 후 결과 받기
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long employeeId = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				Float salary = rs.getFloat(4);
				
				EmployeeVo emp = new EmployeeVo();
				emp.setEmployeeId(employeeId);
				emp.setFirstName(firstName);
				emp.setLastName(lastName);
				emp.setSalary(salary);
				
				list.add(emp);
			}
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally	{
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
		return list;
	}

	@Override
	public List<EmployeeVo> getListByName(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<EmployeeVo> list = new ArrayList<>();
		
		//	검색 로직
		try {
			conn = getConnection();
			String sql = "SELECT employee_id, first_name, " +
						"last_name, salary FROM employees " +
						"WHERE lower(first_name) LIKE ? OR " + 
						" lower(last_name) LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toLowerCase() + "%");
			pstmt.setString(2, "%" + keyword.toLowerCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVo emp = new EmployeeVo();
				emp.setEmployeeId(rs.getLong(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setSalary(rs.getFloat(4));
				
				list.add(emp);
			}
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				
			}
		}
		
		return list;
	}
}
