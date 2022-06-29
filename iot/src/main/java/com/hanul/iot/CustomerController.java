package com.hanul.iot;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.CustomerServiceImpl;
import customer.CustomerVO;

@Controller
public class CustomerController {
	@Autowired 
	private CustomerServiceImpl service;
	
	//고객정보삭제처리 요청
	@RequestMapping("/delete.cu")
	public String delete(int id) {
		//선택한 고객정보를 DB에서 삭제한 후(비지니스로직)
		service.customer_delete(id);
		//응답할 화면연결: 고객목록화면
		return "redirect:list.cu";
	}
	
	//고객정보수정저장처리 요청
	@RequestMapping("/update.cu")
	public String update(CustomerVO vo) {
		//화면에서 수정입력한 정보를 DB에 변경저장한 후(비지니스로직)
		service.customer_update(vo);
		//응답할 화면을 연결: 상세화면으로 연결
		return "redirect:detail.cu?id=" + vo.getId();
	}
	
	
	//고객정보수정화면 요청
	@RequestMapping("/modify.cu")
	public String modify(int id, Model model) {
		//선택한 고객의 정보를 DB에서 조회해와(비지니스로직)
		CustomerVO vo = service.customer_detail(id);
		//수정화면에 출력할 수 있도록
		//Model에 데이터를 담는다
		model.addAttribute("vo", vo);
		//응답화면은 수정화면으로 연결
		return "customer/modify";
	}
	
	//신규고객저장처리 요청
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		//화면에서 입력한 신규고객정보를 DB에 저장(비지니스로직)
		service.customer_insert(vo);
		//고객목록화면으로 연결
		return "redirect:list.cu";
	}
	
	
	//신규고객입력화면 요청
	@RequestMapping("/new.cu")
	public String customer() {
		//신규고객입력화면으로 연결
		return "customer/new";
	}
	
	
	//고객상세정보화면 요청
	@RequestMapping("/detail.cu")
	public String detail(/* @RequestParam */ int id, Model model) {
		//선택한 고객의 정보를 DB에서 조회해와 
		CustomerVO vo = service.customer_detail(id);
		//상세화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		//상세화면으로 연결한다
		return "customer/detail";
	}
	
	
	//고객목록화면 요청
	@RequestMapping("/list.cu")
	public String list(Model model, HttpSession session) {
		session.setAttribute("category", "cu");
		//DB에서 고객목록을 조회해(비지니스로직)와 목록화면에 출력한다
		//Model 타입의 변수에 attribute로 담는다
		//응답할 화면 지정
		List<CustomerVO> list = service.customer_list();
		model.addAttribute("list", list);
		return "customer/list";
	}
	
}
