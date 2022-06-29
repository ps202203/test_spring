package employee;

import java.util.List;

public interface EmployeeService {
	//CRUD(Create, Read, Update, Delete)
	void employee_insert(EmployeeVO vo); 				//신규사원등록
	List<EmployeeVO> employee_list(); 					//사원목록조회(전체)
	List<EmployeeVO> employee_list(String order); 		//정렬(order by)한 사원목록조회(전체)
	List<EmployeeVO> employee_list(int department_id); 	//사원목록조회(부서원)
	EmployeeVO employee_detail(int employee_id); 		//사원정보상세조회
	void employee_update(EmployeeVO vo);	//사원정보변경저장
	void employee_delete(int id);			//사원정보삭제
	
	List<DepartmentVO> employee_department_list(); 	//부서목록조회(사원이 속한 부서목록)
	List<DepartmentVO> department_list();//부서목록조회(회사의 전체부서목록)
	List<JobVO> job_list(); //업무목록조회(회사의 전체업무목록)
}
