<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<H1>HI Everybody </H1>
<h2> do you hear me?</h2>
<h2> 이 메세지는 xxx가 작성 했습니다.</h2>	
<hr>
<h2>현재 시각은
	<%=LocalDateTime.now() %>
	입니다.
</h2>
</body>
</html>