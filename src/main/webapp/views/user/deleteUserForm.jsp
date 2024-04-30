<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/style/form.css">
</head>
<c:import url="/header"/>
<script src="/resources/script/validation-login.js"></script>
<body>
	<section id="root">
	<c:if test="${empty user}">
	<c:redirect url="/login"/>
	</c:if>
	
	
		<h2>회원탈퇴</h2>
		<form method="POST" action="/deleteUser">
			<div>
				<input type="text" id="id" name="id" placeholder="아이디">
				<input type="password" id="password" name="password" placeholder="비밀번호">
			</div>
			<div class="error-container">
				<p class="error-msg" id="error-msg-id">* 아이디: 필수 정보입니다.</p>
				<p class="error-msg" id="error-msg-password">* 비밀번호: 필수 정보입니다.</p>
			</div>
			
			<input type="submit" value="로그인">
		</form>
	</section>
</body>
<c:import url="/footer"></c:import>
</html>