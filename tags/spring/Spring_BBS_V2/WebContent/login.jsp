<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log-in 화면</title>
</head>
<body>
	<form action="/Spring_BBS_V1/login.hs" method="post">
		I D : <input type="text" name="id"><br> PASS : <input
			type="text" name="pass"><br> <input type="submit" value="확인">
		<input type="reset" value="취소" onclick="'/Spring_BBS_V1/list.hs?pageNum=${pageNum}'"> <a
			href="/Spring_BBS_V1/memberJoin.jsp">회원가입</a>
	</form><br>
</body>
</html>