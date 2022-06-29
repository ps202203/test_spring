package member;

import java.util.HashMap;

public interface MemberService {
	//CRUD(Create, Read, Update, Delete)
	boolean member_join(MemberVO vo); //회원가입시 회원정보저장
	MemberVO member_login(HashMap<String, String> map); //로그인처리
	MemberVO member_myinfo(String id);//내정보보기
	boolean member_id_check(String id); //아이디중복확인(아이디존재여부)
	boolean member_update(MemberVO vo); //내정보수정
	boolean member_delete(String id); //회원탈퇴시
	
	String member_salt(String id); //회원id에 해당하는 salt를 조회
	boolean member_reset_password(MemberVO vo); //비밀번호 변경하기
}
