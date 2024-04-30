<%@page import="boardServer.user.UserRequestDto"%>
<%@page import="boardServer.user.UserDao"%>
<%@page import="boardServer.user.UserResponseDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%

String id = request.getParameter("id");
String password = request.getParameter("password");

UserDao userDao = UserDao.getInstance();

UserResponseDto user = userDao.findUserByIdAndPassword(id, password);

if(user == null){
	System.out.println("비밀번호가 틀렸습니다");
	response.sendRedirect("/mypage");
}else{
	UserRequestDto userRequestDto = new UserRequestDto();
	userRequestDto.setId(id);
	userRequestDto.setPassword(password);
	userDao.deleteUser(userRequestDto);	
	response.sendRedirect("/login");
}

%>
</body>
</html>