<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.login {
	width: 250px; margin: 20px auto;
	border: 1px solid #aaa;
	padding: 30px 50px; 
}
.login ul li { margin:10px 0; }
/* 
box-sizing : content-box :  width = 컨텐트 사이즈 
             border-box  :  width = 컨텐트 사이즈 + padding + border
 */
.login input { width:100%; height: 38px; box-sizing: border-box; } 
.login input[type=button] {
	background-color: #3367d6; border: none; color:#fff;
	cursor: pointer;
} 
 
#naver {
	background: url("img/naver_login.png") center no-repeat;
	background-size:cover; border: none; 
}
#kakao {
	background: url("img/kakao_login.png") center no-repeat;
	background-size:cover; border: none; 
}
 
</style>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='img/hanul.logo.png'></a>
	<div class='login'>
	<h3>로그인</h3>
	<ul>
		<li><input type='text' id='id' class='chk' placeholder="아이디"></li>
		<li><input type='password' id='pw' class='chk' placeholder="비밀번호"></li>
		<li><input type='button' value='로그인' onclick="login()"></li>
		<li><hr></li>
		<li><input type='button' id='naver' title='네이버로그인'></li>
		<li><input type='button' id='kakao' title='카카오로그인'></li>
	</ul>
	</div>
	<div><a href='findPw'>비밀번호찾기</a></div>
</div>

<script type="text/javascript">
$('#pw').keyup(function(e){
	if( e.keyCode==13 ) login();
});

function login(){
	if( emptyCheck() ){
		
		$.ajax({
			url: 'iot_login',
			data: { id:$('#id').val(), pw:$('#pw').val() },
			success: function( response ){
				if( response )
					location = '<c:url value="/"/>';
				else{
					alert('아이디나 비밀번호가 일치하지 않습니다!')
					$('#id').focus();
				}
			},error: function(req, text){
				alert( text+':'+req.status);
			}			
		});
	}	
}

</script>

</body>
</html>



