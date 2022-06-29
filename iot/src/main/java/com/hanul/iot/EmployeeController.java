package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import employee.DepartmentVO;
import employee.EmployeeServiceImpl;
import employee.EmployeeVO;
import employee.JobVO;

@Controller
public class EmployeeController {
	@Autowired private EmployeeServiceImpl service;
	
	//신규사원등록 처리 요청
	@RequestMapping("/insert.hr")
	public String insert(EmployeeVO vo) {
		//화면에서 입력한 사원정보를 DB에 저장한 후(비지니스로직)
		service.employee_insert(vo);
		//응답화면연결: 목록화면
		return "redirect:list.hr";
	}
	
	//신규사원등록 화면 요청
	@RequestMapping("/new.hr")
	public String employee(Model model) {
		//전체부서목록, 전체업무목록, 전체사원인매니저목록을 조회해와(비지니스로직)
		List<DepartmentVO> departments = service.department_list();
		List<JobVO> jobs = service.job_list();
		List<EmployeeVO> managers = service.employee_list("name");
		//화면에 출력할 수 있도록 Model에 attribute로 데이터를 담는다
		model.addAttribute("departments", departments);
		model.addAttribute("jobs", jobs);
		model.addAttribute("managers", managers);
		//응답화면연결: 신규사원등록화면		
		return "employee/new";
	}
	
	//사원정보 삭제처리 요청
	@RequestMapping("/delete.hr")
	public String delete(int id) {
		//선택한 사원정보를  DB에서 삭제한 후(비지니스로직)
		service.employee_delete(id);
		//응답화면 연결: 사원목록화면
		return "redirect:list.hr";
	}
	
	//사원정보 변경저장처리 요청
	@RequestMapping("/update.hr")
	public String update(EmployeeVO vo) {
		//화면에서 수정입력한 정보를 DB에 변경저장처리한 후(비지니스로직)
		service.employee_update(vo);
		//응답할 화면연결: 상세화면
		return "redirect:detail.hr?id="+vo.getEmployee_id();
	}
	
	
	//특정사번의 사원정보수정화면 요청
	@RequestMapping("/modify.hr")
	public String modify(int id, Model model) {
		//DB에서 선택한 사원의 정보를 조회해와(비지니스로직)
		EmployeeVO vo = service.employee_detail(id);
		//부서목록, 업무목록을 조회해와(비지니스로직)
		List<DepartmentVO> departments = service.department_list();
		List<JobVO> jobs = service.job_list();
		//정보수정화면에 출력할 수 있도록 Model에 attribute로 데이터를 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("departments", departments);
		model.addAttribute("jobs", jobs);
		//응답화면연결: 정보수정화면
		return "employee/modify";
	}
	
	
	//특정사번의 사원상세정보화면 요청
	@RequestMapping("/detail.hr")
	public String detail(int id, Model model) {
		//DB에서 선택한 사원의 정보를 조회해와(비지니스로직)
		EmployeeVO vo = service.employee_detail(id);
		//상세정보화면에 출력할 수 있도록 Model에 attribute로 데이터를 담는다
		model.addAttribute("vo", vo);
		//응답화면연결: 상세정보화면
		return "employee/detail";
	}
	
	//사원목록화면 요청
	@RequestMapping("/list.hr")
	public String list(HttpSession session, Model model
						, @RequestParam(defaultValue = "-1") int id) {
		/*
		 * String param = request.getParameter("id"); //id 라는 파라미터가 존재하지 않으면 param 은
		 * null 이고 //id 라는 파라미터는 존재하나 데이터값이 없으면 ""
		 */
		
		session.setAttribute("category", "hr");
		//DB에서 사원목록을 조회해와(비지니스로직)
		List<EmployeeVO> list = null;
		
		if( id==-1 )
			list = service.employee_list("employee_id"); //사번순으로 정렬
		else 
			list = service.employee_list(id);
		
		//DB에서 사원이 속한 부서목록을 조회해와(비지니스로직)
		List<DepartmentVO> departments 
					= service.employee_department_list();
		
		//사원목록화면에 출력할 수 있도록 Model에 attribute로 데이터를 담는다
		model.addAttribute("list", list);
		model.addAttribute("departments", departments);
		model.addAttribute("id", id);
		
		//응답화면연결: 목록화면
		return "employee/list";
	}
	
}
