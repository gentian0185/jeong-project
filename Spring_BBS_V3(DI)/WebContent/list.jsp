<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<body>
	<center>
		<b>글목록(전체 글 : ${count})</b>
		<table width="700">
			<tr>
				<td align="left">ID : ${id }</td>
				<td align="right"><a href="logout.jsp">로그아웃 </a><a href="/Spring_BBS_V1/writeForm.hs"> 글쓰기 </a></td>
			</tr>
		</table>

		<c:if test="${count==0 }">
			<table width="700" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center">게시판에 저장된 글이 없습니다.
				</tr>
			</table>
		</c:if>
		<table border="1" width="700" cellpadding="2" cellspacing="2"
			align="center">
			<tr height="30">
				<th width="50">번호</th>
				<th width="250">제목</th>
				<th width="100">아이디</th>
				<th width="150">날짜</th>
				<th width="50">조회수</th>
			</tr>

			<c:forEach var="article" items="${article }">
				<tr height="30">
					<td align="center" width="50"><c:out
							value="${article.article_num }" /></td>
					<td width="250">
						<c:if test="${ article.depth > 0}">
							<img src="images/level.gif" width="${10 * article.depth}"  height="16">
							<img src="images/re.gif">
								
						</c:if> <c:if test="${ article.depth == 0}">
							<img src="images/level.gif" width="0" height="16">
						</c:if> <!-- article_num 등을 입력받을때 공백 포함시키면 안된다!!!! -->
						<a href="/Spring_BBS_V1/content.hs?article_num=${article.article_num }&pageNum=${pageNum}">
							${article.title }</a> <c:if test="${ article.hit >= 20}">
							<img src="images/hot.gif" border="0" height="16">
						</c:if></td>

					<td align="center" width="100">${article.id }</td>
					<td align="center" width="150">${article.write_date }</td>
					<td align="center" width="50">${article.hit }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center" height="40">${pageCode}</td>
			</tr>
		</table>
	</center>
</body>
</html>