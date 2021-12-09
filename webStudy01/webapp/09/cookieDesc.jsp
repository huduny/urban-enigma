<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie</title>
</head>
<body>
	:http stateless의 단점을 보완하기 위해 최소한의 상태 정보를 클라이언트사이드에 저장하는 개념.
	
	쿠키사용단계
	1. 쿠키생성(name, value 필수속성)
	2.response(set-cookie 헤더)에 쿠키를 포함하여 클라이언트로 전송.
	
	3.response에 포함된 쿠키를 자기 쿠키 저장소에 저장
	4.다음번 request에 해당 쿠키를 서버로 재전송.
</body>
</html>