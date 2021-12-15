<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO member = (MemberVO) request.getAttribute("member");
%>
<table>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>주소1</th>
			<th>이메일</th>
			<th>마일리지</th>
			<th>생일</th>
		</tr>
</table>
</body>
</html>