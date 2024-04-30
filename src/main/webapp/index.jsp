<%@page import="boardServer.user.model.UserRequestDto"%>
<%@page import="boardServer.user.model.UserDao"%>
<%@page import="boardServer.user.model.UserResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<jsp:include page="/header"></jsp:include>
<body>
	<section id="root">
	<%
	UserDao userDao = UserDao.getInstance();
	%>
	</section>
</body>
<jsp:include page="/footer"></jsp:include>
</html>