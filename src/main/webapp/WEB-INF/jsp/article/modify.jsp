<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- <c:set var="pageName" value="게시물 수정" /> 를 미리 jsp에 올려 놓았어야 함 -->
<!--  <@ include file=""../part/head.jspf" > 로 대체할 수 있지만 안 함:-->  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resource/common.css">
<title>커뮤니티 사이트 - 게시 수정</title>
</head>
<body>
	<h1 class="con">게시물 수정</h1>
	<script>
		function submitModifyForm(form) {
			form.title.value= form.title.value.trim();
			if (form.title.value.length == 0) {
				alert('제목을 입력해 주세요.');
				form.title.focus;
				return false;
			}
			
			form.body.value= form.body.value.trim();
			if (form.body.value.length == 0) {
				alert('내용을 입력해 주세요.');
				form.body.focus;
				return false;
			}
			
			form.submit();
		}
	</script>
	
	<form class="con common-form" action="./doModify" method="POST" 
		onsubmit="submitModifyForm(this); return false;">
		<input type="hidden" name="id" value="${article.id}">	<!-- id 추가되어야 함: -->
		<div>
			<span> 제목 </span>
			<div>
				<input name="title" type="text" placeholder="제목" 
				autofocus="autofocus" value="${article.title}">
			</div>
		</div>
		<div>
			<span>내용 </span>
			<div>
				<textarea name="body" placeholder="내용">${article.body}
				</textarea>

			</div>
		</div>
		<div>
			<span> 수정 </span>
			<div>
				<input type="submit" value="수정">
				<input type="reset" value="취소" onclick="history.back();">
			</div>
		</div>
	</form>
	
	<div class="btns con">
		<a href="./list">게시물 리스트</a>
		<a href="./add">게시물 추가</a>
		<a onclick="if (confirm('삭제하시겠습니까')==false) return false;" href="./doDelete?id=${article.id}">게시물 삭제</a>
	</div>
</body>
</html>