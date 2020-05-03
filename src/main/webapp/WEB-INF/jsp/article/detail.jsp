<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!-- <c:set var="pageName" value="게시물 리스트" /> 미리 올려놓아야 head.jspf에서 사용-->  
<!--  <@ include file=""../part/head.jspf" > 로 대체할 수 있지만 안 함:-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resource/common.css">
<title>커뮤니티 사이트 - 게시 리스트</title>
</head>
<body>
	<h1 class="con">게시물 상세 페이지</h1>
	<section class="con">
		번호 : ${article.id}<br>
		제목 : ${article.title}<br>
		내용 : ${article.body}<br>
		조회수 : ${article.hit}
	</section>
	<!--  <hr> --> 
	<div class="btns con">
		<a href="./list">게시물 리스트</a>
		<a href="./add">게시물 추가</a>
		<a href="./modify?id=${article.id}">게시물 수정</a>
		<a onclick="if (confirm('삭제하시겠습니까')==false) return false;" href="./doDelete?id=${article.id}">게시물 삭제</a>
	</div>
</body>
</html>