<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<!-- 내함수는 뒤쪽에 놓아야 한다. -->
<script type="text/javascript" src="js/comment.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글읽기</title>
</head>
<body>
	<!-- 수정 누를 경우  -->
	<form action="/Spring_BBS_V5(Anno-Ibatis)/replyForm.hs" method=post>
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="article_num" id="article_num" value="${oneArticle.article_num }">
		<input type="hidden" name="depth" value="${oneArticle.depth }">
		<input type="hidden" name="group_id" value="${oneArticle.group_id }">
		<input type="hidden" name="pos" value="${oneArticle.pos }">
		<!-- 값 전달이 안된다... content의 타입이 clob라 안된다.. 그냥 DB로.. 아래 세줄은 안쓴다 생각하자. -->
		<input type="hidden" name="title" value="${oneArticle.title}">
		<input type="hidden" name="content" value="${oneArticle.content }">
		<input type="hidden" name="file_name" value="${oneArticle.file_name }">
		<table border="1" width="500" align="center">
			<tr>
				<td>글쓴이 :</td>
				<td>${oneArticle.id }</td>
				<td>조회수 :</td>
				<td>${oneArticle.hit }</td>
			</tr>
			<tr>
				<td>제목 :</td>
				<td>${oneArticle.title }</td>
				<td>날짜 :</td>
				<td>${oneArticle.write_date}</td>

			</tr>
			<tr>
				<td colspan="2">다운로드</td>
				<td colspan="2"><a
					href="/Spring_BBS_V5(Anno-Ibatis)/download.hs?file_name=${oneArticle.file_name }">${oneArticle.file_name }</a></td>
			</tr>
			<tr>
				<td colspan="4"><xmp>${oneArticle.content }</xmp></td>
			</tr>
			<tr>
				<c:if test="${id !=null }">
					<td colspan="4" align="right"><input type="submit"
						value="답글달기"> <c:if test="${id ==oneArticle.id }">
							<!-- id가 있으면 답글만 나오고 아이디가 같으면 수정 삭제 가능. 같지 않으면 수정 삭제 가능. 모두 목록으로 가능. -->
							<input type="button" value="수정하기" name="article_num" value="${oneArticle.article_num }"
								onclick="document.location.href='/Spring_BBS_V5(Anno-Ibatis)/updateForm.hs?article_num=${oneArticle.article_num }&pageNum=${pageNum}'">
							<input type="button" value="삭제하기" name="article_num"
								onclick="document.location.href='/Spring_BBS_V5(Anno-Ibatis)/deleteForm.hs?article_num=${oneArticle.article_num }&pageNum=${pageNum}'">
	
						</c:if> <c:if test="${id != oneArticle.id }">
							<input type="button" value="수정하기" disabled="disabled">
							<input type="button" value="삭제하기" disabled="disabled">
						</c:if> <input type="button" value="목록으로"
						onclick="document.location.href='/Spring_BBS_V5(Anno-Ibatis)/list.hs?pageNum=${pageNum}'">
					</td>
				</c:if>
				<c:if test="${id ==null }">
					<td colspan="4" align="right"><input type="submit"
						value="답글달기" disabled="disabled"> <input type="button"
						value="수정하기" disabled="disabled"> <input type="button"
						value="삭제하기" disabled="disabled"> <input type="button"
						value="목록으로"
						onclick="document.location.href='/Spring_BBS_V5(Anno-Ibatis)/list.hs?pageNum=${pageNum}'"></td>
				</c:if>
			</tr>
			<tr>
				<td colspan="3"'>
					<input type="button" value="comment 보기" id="comment_read">
				</td>
				<td>
					<textarea id="comment_content"></textarea>
					 <input	type="button" value="comment달기" id="comment_write">
				</td>
			</tr>

			<tr>
			<!-- ajax 이용 -->
			<td colspan="4"><div id="show_comment"></div></td>
			
		</table>
		<% out.println( "request.getMethod()=="+ request.getMethod()); %>
	</form>

</body>
</html>