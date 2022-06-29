package common;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	//재발급 비밀번호전송하기
	public String resetPassword(String id, String name, String email) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp.naver.com");
		mail.setDebug(true);
		mail.setCharset("utf-8");
		
		mail.setAuthentication("sunpark98", "dlglfhd132");
		mail.setSSLOnConnect(true); //로그인버튼클릭하기
		
		String pw = "";
		try {
			//메일송신자
			mail.setFrom("sunpark98@naver.com", "관리자");			
			mail.addTo(email, name); //메일수신자
			
			//임시비밀번호로 사용할 랜덤문자열 생성
			pw = UUID.randomUUID().toString();
			//adfaf-ger7798af-adgarae234da
			pw = pw.substring( pw.lastIndexOf("-")+1 );
			
			mail.setSubject("비밀번호 재발급"); //제목쓰기
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>임시 비밀번호 발급</h3>");
			msg.append("<p>아이디: ").append(id).append(" 님</p>");
			msg.append("<p>발급된 임시 비밀번호로 로그인후 비밀번호를 변경하세요</p>");
			msg.append("<p><strong>" + pw + "</strong></p>");
			msg.append("</body>");
			msg.append("</html>");			
			mail.setHtmlMsg( msg.toString() ); //내용쓰기
			
			mail.send(); //전송하기버튼 클릭
		}catch(Exception e) {			
		}
		return pw;
	}
	
	//salt를 사용해 비밀번호를 암호화하기
	public String getEncrypt(String pw, String salt) {
		pw = pw + salt;
		//암호화방식 지정
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update( pw.getBytes() );
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte d : digest) {
				sb.append(String.format("%02x", d));
			}
			pw = sb.toString();
		}catch(Exception e) {			
		}
		return pw;
	}
	
	//비밀번호를 해시 처리하는 단방향 함수의 랜덤데이터만들기
	public String generateSalt() {
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[24];
		r.nextBytes(salt);
		
		StringBuffer buf = new StringBuffer();
		for(byte s : salt) {
			buf.append(String.format("%02x", s));
		}
		return buf.toString();
	}
	
}
