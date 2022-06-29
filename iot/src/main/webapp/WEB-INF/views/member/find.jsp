<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.reset {
	width: 250px;	 margin: 20px auto; border:1px solid #aaa;
	padding: 30px 50px;
}
.reset input { width: 100%; box-sizing: border-box; height: 38px; }
.reset li:not(:last-child) { margin:10px 0;}
.reset input[type=button] {
	background-color: #3367d6; color:#fff; border:none;  } 
</style>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='img/hanul.logo.png'></a>
	<div class='reset'>
		<h3>비밀번호 재발급</h3>
		<form method='post' action='resetPw'>
		<ul>
			<li><input type='text' class='chk' name='id' placeholder="아이디"></li>
			<li><input type='text' class='chk' name='name' placeholder="이름"></li>
			<li><input type='text' class='chk' name='email' placeholder="이메일"></li>
			<li><input type='reset' value='다시입력'></li>
			<li><input type='button' value='확인' 
					onclick="if( emptyCheck() ) $('form').submit()"></li>
		</ul>
		</form>
	</div>
</div>
</body>
</html>