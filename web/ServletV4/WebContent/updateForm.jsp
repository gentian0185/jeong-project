<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/BBSExampleV2/update.hs" method="post">
		<input type="hidden" name="article_num" value="${article.article_num }"> 
		<input type="hidden" name="pageNum" value="${pageNum }">
		<table border="2" width="200">
			<tr>
				<td>글쓴이 :</td>
				<td>${article.id }</td>
			</tr>
			<tr>
				<td>제목 :</td>
				<td><input type="text" name="title" value = "${article.title }"></td>

			</tr>
			<tr>
				<td colspan="2"><textarea cols="50" rows="20" name="content" >${article.content }
				</textarea></td>
			</tr>
			<tr>
				<td>첨부 :</td>
				<td><input type="file" name="file_name"></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정하기"></td>
				<td><input type="reset" value="수정 취소"
					onclick="document.location.href='/BBSExampleV2/list.hs'"></td>
			</tr>
		</table>
	</form>
</body>
</html>