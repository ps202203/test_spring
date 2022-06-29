package com.hanul.iot;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonService common;
	
	//비밀번호변경처리 요청
	@RequestMapping("/changePw")
	public String change(HttpSession ss,
							@RequestParam String pw) {
		//화면에서 입력한 비밀번호를 새로운 비밀번호로 하여 DB에 변경저장
		//기존의 salt를 사용해 새로 입력한 비밀번호를 암호화한다.
		MemberVO vo = (MemberVO)ss.getAttribute("loginInfo");
		pw = common.getEncrypt(pw, vo.getSalt());
		vo.setSalt_pw(pw);
		service.member_reset_password(vo);
		//세션의 로그인사용자정보를 변경된 정보로 바꿔 담는다
		ss.setAttribute("loginInfo", vo);
		
		//응답화면 연결 - 웰컴페이지
		return "redirect:/";
	}
	
	
	//로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession ss) {
		//세션에 저장된 로그인사용자 정보를 삭제
		ss.removeAttribute("loginInfo");
		return "redirect:/";
	}
	
	//비밀번호변경화면 요청
	@RequestMapping("/password")
	public String change(HttpSession ss) {
		//로그인된 상태인 경우만 화면 연결 가능
		MemberVO vo = (MemberVO)ss.getAttribute("loginInfo");
		if( vo==null )
			return "redirect:login";
		else {			
			ss.setAttribute("category", "password");
			return "member/password";
		}
	}
	
	//비밀찾기재발급 요청
	@ResponseBody 
	@RequestMapping(value="/resetPw"
					, produces="text/html; charset=utf-8" )
	public String reset(MemberVO vo) {
		//화면에서 입력한 아이디에 대한 임시비밀번호를 입력한 이메일로 전송한다
		//임시비밀번호를 랜덤하게 만들어내도록 한다
		String pw = common.resetPassword(
						vo.getId(), vo.getName(), vo.getEmail());
		
		//비밀번호를 만들기 위해서 암호화에 사용할 salt
		String salt = common.generateSalt();
		
		//임시발급된 비밀번호를 salt를 사용해 암호화한다.
		pw = common.getEncrypt(pw, salt);  //암호화된 비밀번호
		
		//화면에서 입력한 아이디에 대해 salt, 암호화된 비밀번호로 DB에 변경저장한다
		vo.setSalt(salt);
		vo.setSalt_pw(pw);
		boolean reset = service.member_reset_password(vo);
		
		//응답할 화면 - 비밀번호가 정상 발급된 경우 로그인화면
		//                       발급되지 않은 경우 비밀번호 재발급화면
		StringBuffer msg = new StringBuffer();		
		msg.append("<script>");
		if( reset ) {
			msg.append("alert('임시 비밀번호가 발급되었습니다^^\\n이메일을 확인하세요');")
				.append("location='login';");
			
		}else {
			msg.append("alert('임시 비밀번호발급 실패ㅠㅠ'); history.go(-2);");
		}
		msg.append("</script>");
		return msg.toString();
	}
	
	//비밀찾기화면 요청
	@RequestMapping("/findPw")
	public String find(HttpSession session) {
		session.setAttribute("category", "find");
		return "member/default/find";
	}
	
	//로그인처리 요청
	@ResponseBody @RequestMapping("/iot_login")
	public boolean login(String id, String pw, HttpSession session) {
		//회원id에 해당하는 salt를 조회
		String salt = service.member_salt(id);
		//salt 를 사용해 로그인시 입력한 비밀번호를 암호화한 후 
		//암호화 한 비밀번호를 사용해 로그인한다
		pw = common.getEncrypt(pw, salt);
		
		//화면에서 입력한 아이디와 비밀번호가 일치하는 회원정보가 DB에 있는지 조회한 후(비지니스로직)
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = service.member_login(map);
		
		//일치하는 회원정보를 세션에 attribute로 담는다
		session.setAttribute("loginInfo", vo);
		
		//로그인여부를 응답한다.
		return vo==null ? false : true;
	}
	
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		//응답화면연결 -로그인화면
		return "member/default/login";
	}
}
