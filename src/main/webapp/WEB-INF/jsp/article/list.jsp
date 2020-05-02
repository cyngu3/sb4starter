<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!-- 아직 아님:: <c:set var="pageName" value="게시물 리스트" /> -->   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 사이트 - 게시 리스트</title>
</head>
<body>
	<h1>게시 리스트 페이지</h1>
	<h2>전체 게시물 개수 : ${totalCount} </h2>
	<!-- el의 장점은  request.setAttribute("article") 할 필요가 없다 -->
	<!-- 참고: "게시물 리스트를 자바버전(el)(나중에 제거 함), jstl 버전 2가지로 생성 되었었음  -->
	<!-- 아래 온 곳은: ${list}는 :from::List<Article> list = articleService.getList();-->
	<c:forEach items="${list}" var="article">
		<section>번호 : ${article.id}, 제목: ${article.title}, 내용: ${article.body} </section>
	<hr> 
	</c:forEach>
</body>
</html>