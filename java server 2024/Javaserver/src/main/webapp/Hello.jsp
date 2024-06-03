<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1> Hi everybody</h1>
<h2> Do you here me?</h2>
<h2> 이 메세지는 변성훈님이 작성했습니다. </h2>
<hr>
<h2> 현재 시각은
	<%=LocalDateTime.now() %>
	입니다. 그쵸
</h2>
</body>
</html>