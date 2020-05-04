<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageName" value="메인  Home" />
<%@ include file="../part/head.jspf"%>

<div> <!--  임시 나중 사용 안함:  BeformActionInterceptor에서 부여하여 가져오는 것:-->
	 <!-- 확인용 테스트 넘버: ${testNumber}  => 확인 됨: cy200504 -->
</div>

<c:if test="${loginedMember != null}"> <!-- BeforeActionInterceptor에서 가져오는 것:WebMvcConfig -->
	<div class="con">회원 로그인 ID : ${loginedMember.loginId}</div>
</c:if>

<%@ include file="../part/foot.jspf"%>