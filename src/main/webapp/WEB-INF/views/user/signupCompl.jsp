<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<link rel="stylesheet" href="<c:url value='/resources/css/signup.css'/>">
	<h2 style="position:relative;left:20%;">>회원가입 완료</h2>
	<div style="width:60%;position:relative;left:20%;padding:10px 30px 5px;border:0.5px solid grey;">
		<h2 style="color:blue;">EBS 회원가입이 성공적으로 완료 되었습니다!</h2>
		<h2 style="position:relative;top:-20px;">창의교육ㆍ미래방송 EBS에 오신 걸 환영합니다.</h2>
	</div>
	<br>
	<div style="height:30px;width:60%;position:relative;left:20%;padding:10px 30px 5px;border:0.5px solid grey;">
	</div>
	<br>
	<div style="text-align:center;display:flex;justify-content: center;">
		<button type="button" class="button" onclick="location.href='/'">홈페이지로 이동</button>
	</div>
</body>
</html>