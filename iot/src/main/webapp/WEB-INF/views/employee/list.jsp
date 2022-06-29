<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>사원목록</h3>
<form method='post' action='list.hr'>
<div id='list-top' class='w-px1000'>
<ul>
	<li>부서명</li>
	<li>
		<select class='w-px160' name='id' onchange='$("form").submit()'>
			<option ${id eq -1 ? 'selected' :''} value='-1'>전체</option>
			<c:forEach items="${departments}" var="vo">
			<option ${id eq vo.department_id ? 'selected' :''} value='${vo.department_id}'>${vo.department_name}</option>
			</c:forEach>
		</select>
	</li>
</ul>
<ul><li><a class='btn-fill' href='new.hr'>신입사원등록</a></li>
</ul>
</div>
</form>

<div class='tb-wrap w-px1000' style='height:495px'>
<table class='tb-list' style='width:100%' >
<colgroup>
	<col width="80px">
	<col width="180px">
	<col width="300px">
	<col>
	<col width="100px">
</colgroup>
<tr><th>사번</th>
	<th>성명</th>
	<th>부서명</th>
	<th>업무</th>
	<th>급여</th>
</tr>
<c:forEach items="${list }" var="vo">
<tr><td>${vo.employee_id}</td>
	<td><a href='detail.hr?id=${vo.employee_id}'>${vo.last_name} ${vo.first_name}</a></td>
	<td>${vo.department_name}</td>
	<td>${vo.job_title}</td>
	<td class='text-right' style='padding-right:10px;'>
		<fmt:formatNumber value="${vo.salary}"/> </td>
</tr>
</c:forEach>
</table>
</div>

</body>
</html>