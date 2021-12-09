<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
확인
<form method="post"> 
	<select name="member">
	<option value>멤버 선택</option>
<%
		List<BtsVO> list = (List) request.getAttribute("btsList");
		
		for (BtsVO btsVO : list) {
			String id = btsVO.getId();
			String name = btsVO.getName();
%>	
		<option value="<%=id%>"><%=name%></option>
<%					
		}
	%>
	
		
	</select>
	<input type="submit" value="전송" />
</form>
</body>
</html>