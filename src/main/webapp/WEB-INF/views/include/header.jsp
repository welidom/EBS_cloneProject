<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EBS 온라인클론스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" type="text/css">
</head>
<body style="background-color: white;">
	<div class="header">
		<table>
		<tr>
			<th width="700spx"><a href="main.do" class="defaultButton">EBS 온라인클론스</a></th>
			<td width="300px"></td>
			<td><a href="manual.do" class="defaultButton">이용자메뉴얼</a></td>
			<td><a href="notice.do" class="defaultButton">공지사항</a></td>
			<td><a class="defaultButton" href="faqList.do">FAQ</a></td>
			<c:choose>
				<c:when test="${UserId eq null}">
					<td><a href="login.do" class="defaultButton" style="border-radius:100px; background-color:#2580EB; color:white; padding: 7px 25px;">로그인</a></td>
					<td><a href="signUp.do" class="defaultButton" style="border-radius:100px; background-color:lightgray; color:black; padding: 7px 25px;">회원가입</a></td>
				</c:when>
				<c:otherwise>
					<td>
						<a href="logout.do" class="defaultButton" style="border-radius:100px; background-color:#2580EB; color:white; padding: 7px 25px;">로그아웃</a>
					</td>
				</c:otherwise>
			</c:choose>
		</tr>
		</table>
	</div>