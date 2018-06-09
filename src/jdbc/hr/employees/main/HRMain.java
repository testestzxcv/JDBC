package jdbc.hr.employees.main;

import java.util.Iterator;
import java.util.List;

import jdbc.hr.employees.dao.EmployeeDao;
import jdbc.hr.employees.vo.EmployeeVo;

public class HRMain {

	public static void main(String[] args) {
		System.out.println("사원 목록");
		System.out.println("==========");
		
		//	DAO로부터 전체 Employee 목록을 불러옵시다
		/*
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.getList();
		
		Iterator<EmployeeVo> iter = list.iterator();
		
		
		while(iter.hasNext()) {
			EmployeeVo emp = iter.next();
			System.out.printf("Name: %s %s, Salary: %f%n",
					emp.getFirstName(), emp.getLastName(),
					emp.getSalary());
		}
		*/
		
		//	keyword를 이용한 Name 검색
		EmployeeDao dao = new EmployeeDao();
		String keyword = "ng";
		System.out.println("Employee Search: keyword = " + keyword);
		
		List<EmployeeVo> list = dao.getListByName(keyword);
		Iterator<EmployeeVo> iter = list.iterator();
		
		while(iter.hasNext()) {
			EmployeeVo emp = iter.next();
			
			System.out.printf("Name: %s %s, Salary: %f%n", 
					emp.getFirstName(),
					emp.getLastName(),
					emp.getSalary());
		}
	}

}
