<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.valid { color: green; font-size: 14px }	 
.invalid { color: red; font-size: 14px }	 
</style>
<script src="js/member_check.js"></script>
</head>
<body>
<div>
	<div class='reset'>
		<h3>비밀번호변경</h3>
		<form method='post' action='changePw'>
		<ul>
			<li><input type='password' class='chk' name='pw' placeholder="새로운 비밀번호">
				<div class='valid text-left'>비밀번호를 입력하세요</div>
			</li>
			<li><input type='password' class='chk' name='pw_ck' placeholder="새로운 비밀번호 확인">
				<div class='valid text-left'>비밀번호를 다시 입력하세요</div>
			</li>
			<li><input type='reset' value='다시입력'></li>
			<li><input type='button' value='확인' 
						onclick="$('form').submit()"></li>
		</ul>
		</form>
	</div>
</div>
<script>
$('.chk').keyup(function(){
	var value = member.tag_status( $(this) );
	$(this).siblings('div')
			.removeClass('valid')
			.removeClass('invalid')
			.addClass(value.code)
			.text(value.desc);
});

/*  
$('[name=pw]').keyup(function(){
	var value = member.tag_status( $(this) );
	if( value.code=='invalid' ) console.log( value.desc );
});
$('[name=pw_ck]').keyup(function(){
	var value = member.tag_status( $(this) );
	if( value.code=='invalid' ) console.log( value.desc );
});
*/

</script>

</body>
</html>