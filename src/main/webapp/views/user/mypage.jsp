<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<jsp:include page="/header"></jsp:include>
<body>

	<c:if test="${empty user}">
		<c:redirect url="/login"></c:redirect>
	</c:if>
	<section id="root">
		<h1>${user.name}님환영합니다!</h1>

		<br>
		<button onclick="location.href = '/updateUserForm'">정보 변경</button>
		<button onclick="location.href='/deleteUserForm'">유저 탈퇴</button>
	</section>
</body>
<jsp:include page="/footer"></jsp:include>
</html>