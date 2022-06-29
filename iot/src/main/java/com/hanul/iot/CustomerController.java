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
	
	//����������ó�� ��û
	@RequestMapping("/delete.cu")
	public String delete(int id) {
		//������ �������� DB���� ������ ��(�����Ͻ�����)
		service.customer_delete(id);
		//������ ȭ�鿬��: �����ȭ��
		return "redirect:list.cu";
	}
	
	//��������������ó�� ��û
	@RequestMapping("/update.cu")
	public String update(CustomerVO vo) {
		//ȭ�鿡�� �����Է��� ������ DB�� ���������� ��(�����Ͻ�����)
		service.customer_update(vo);
		//������ ȭ���� ����: ��ȭ������ ����
		return "redirect:detail.cu?id=" + vo.getId();
	}
	
	
	//����������ȭ�� ��û
	@RequestMapping("/modify.cu")
	public String modify(int id, Model model) {
		//������ ���� ������ DB���� ��ȸ�ؿ�(�����Ͻ�����)
		CustomerVO vo = service.customer_detail(id);
		//����ȭ�鿡 ����� �� �ֵ���
		//Model�� �����͸� ��´�
		model.addAttribute("vo", vo);
		//����ȭ���� ����ȭ������ ����
		return "customer/modify";
	}
	
	//�ű԰�����ó�� ��û
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		//ȭ�鿡�� �Է��� �ű԰������� DB�� ����(�����Ͻ�����)
		service.customer_insert(vo);
		//�����ȭ������ ����
		return "redirect:list.cu";
	}
	
	
	//�ű԰��Է�ȭ�� ��û
	@RequestMapping("/new.cu")
	public String customer() {
		//�ű԰��Է�ȭ������ ����
		return "customer/new";
	}
	
	
	//��������ȭ�� ��û
	@RequestMapping("/detail.cu")
	public String detail(/* @RequestParam */ int id, Model model) {
		//������ ���� ������ DB���� ��ȸ�ؿ� 
		CustomerVO vo = service.customer_detail(id);
		//��ȭ�鿡 ����� �� �ֵ��� Model�� ��´�
		model.addAttribute("vo", vo);
		//��ȭ������ �����Ѵ�
		return "customer/detail";
	}
	
	
	//�����ȭ�� ��û
	@RequestMapping("/list.cu")
	public String list(Model model, HttpSession session) {
		session.setAttribute("category", "cu");
		//DB���� ������� ��ȸ��(�����Ͻ�����)�� ���ȭ�鿡 ����Ѵ�
		//Model Ÿ���� ������ attribute�� ��´�
		//������ ȭ�� ����
		List<CustomerVO> list = service.customer_list();
		model.addAttribute("list", list);
		return "customer/list";
	}
	
}
