<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<form action="main.do" id="login" method="post">
	<h1><a href="#" onclick="document.getElementById('login').submit();">온라인 클론스로 이동</a></h1>
	<input type="radio" id="no" name="login" value="0" checked>
	<label for="no">로그인 안함</label>
	<input type="radio" id="student" name="login" value="1">
	<label for="student">학생 로그인</label>
	<input type="radio" id="teacher" name="login" value="2">
	<label for="teacher">선생님 로그인</label>
	<input type="radio" id="admin" name="login" value="3">
	<label for="admin">관리자 로그인</label>
</form>
</body>
</html>