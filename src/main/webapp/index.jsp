<%@page import="boardServer.user.UserRequestDto"%>
<%@page import="boardServer.user.UserDao"%>
<%@page import="boardServer.user.UserResponseDto"%>
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
	pageContext.setAttribute("test", "page data");
	request.setAttribute("test", "request data");
	session.setAttribute("test", "session data");
	application.setAttribute("test", "application data");
	
	
	//단순 페이지 이동 , 주소표시줄 변경
	//request.sendRedirect("test1.jsp");
	
	//request,response객체를 전달하면서 이동하는 방법 , 주소표시줄(url)변경 x
	request.getRequestDispatcher("test1.jsp").forward(request, response);
	%>
	</section>
</body>
<jsp:include page="/footer"></jsp:include>
</html>