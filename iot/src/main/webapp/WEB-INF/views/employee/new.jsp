<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>신입사원등록</h3>
<form method='post' action='insert.hr'>
<table class='w-px500'>
<tr><th class='w-px120'>성명</th>
	<td><input class='w-px120' type='text' name='last_name' placeholder="성"> 
		<input class='w-px120' type='text' name='first_name' placeholder="명"> 
	</td>
</tr>
<tr><th>부서명</th>
	<td><select name='department_id'>
			<option value='-1'>부서선택</option>
			<c:forEach items="${departments}" var="d">
			<option value='${d.department_id}' ${vo.department_id eq d.department_id ? 'selected' : ''}>${d.department_name}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr><th>업무</th>
	<td><select name='job_id'>
			<c:forEach items="${jobs}" var="j">
			<option value='${j.job_id}' ${vo.job_id eq j.job_id ? 'selected' : ''}>${j.job_title}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr><th>이메일</th>
	<td><input type='text' name='email' ></td>
</tr>
<tr><th>전화번호</th>
	<td><input type='text' name='phone_number' ></td>
</tr>
<tr><th>입사일자</th>
	<td><input type='date' name='hire_date' ></td>
</tr>
<tr><th>급여</th>
	<td><input type='text' name='salary' ></td>
</tr>
<tr><th>관리자</th>
	<td><select name='manager_id'>
			<option value='-1'>관리자선택</option>
			<c:forEach items="${managers}" var="m">
			<option value='${m.employee_id}'>${m.name}</option>
			</c:forEach>
		</select>
	</td>
</tr>
</table>
</form>
<div class='btnSet'>
<a class='btn-fill' onclick="$('form').submit()">저장</a>
<a class='btn-empty'>취소</a>
</div>

</body>
</html>