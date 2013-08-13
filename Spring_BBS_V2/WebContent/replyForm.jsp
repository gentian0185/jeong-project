<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Spring_BBS_V1/reply.hs" method="post">
		<input type="hidden" name="id" value="${id }">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="depth" value="${depth }">
		<input type="hidden" name="group_id" value="${group_id }">
		<input type="hidden" name="pos" value="${pos }">
		<table border="2" width="200">
			<tr>
				<td>글쓴이 :</td>
				<td>${id }</td>
			</tr>
			<tr>
				<td>답변 제목 :</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="50" rows="20" name="content">
				</textarea></td>
			</tr>
			<tr>
				<td>첨부 :</td>
				<td><input type="file" name="file_name"></td>
			</tr>
			<tr>
				<td><input type="submit" value="답글달기"></td>
				<td><input type="reset" value="취소" onclick="document.location.href='/Spring_BBS_V1/list.hs?${pageNum}'"></td>
			</tr>
		</table>
	</form>
</body>
</html>