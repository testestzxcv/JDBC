package jdbc.hr.employees.dao;

import java.util.List;

import jdbc.hr.employees.vo.EmployeeVo;

public interface IEmployeeDao {
	// 규약
	public List<EmployeeVo> getList();
	public List<EmployeeVo> getListByName(String keyword);
	
}
