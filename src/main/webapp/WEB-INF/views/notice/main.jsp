<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/manual.css" type="text/css">
<div style="background-color: #2580EB; padding: 0 14%; color:white; display: inline-block; width: 72%">
	<span style="float: left; padding-top: 30px">
	HOME 〉 공지사항<br>
	<br>
	<b style="font-size: 25px;">공지사항</b><br>
	EBS온라인클레스와 교육부의 새로운 소식, 시스템 점검 동을 안내합니다.<br>
	</span>
	<img style="float: right;" alt="manual.png" src="${pageContext.request.contextPath}/resources/images/manual.png">
</div>
<div style="padding: 40px 14% 20px 14%;">
	<div>
		<table style="border-top: 2px solid #2580EB; width: 100%" class="manual">
		<tr style="background-color: ; border-top: 2px solid #2580EB;">
			<th style="width: 5%">번호</th>
			<th style="padding : 15px 0; width: 10%;">구분</th>
			<th>제목</th>
			<th style="width: 10%;">첨부파일</th>
			<th style="width: 10%;">등록일</th>
			<th style="width: 10%;">조회수</th>
		</tr>
		<c:forEach items="${notice}" var="dto">
		<tr>
			<td align="center" style="padding: 15px 0;">${dto.category}</td>
			<td>${dto.subject}</td>
			<c:choose>
				<c:when test="${dto.content ne ''}"><td align="center"><a href="${dto.content}" style="font-size: 20px; color:black;" download><i class="fas fa-paperclip"></i></a></td></c:when>
				<c:otherwise><td align="center"></td></c:otherwise>
			</c:choose>
			<td align="center">${dto.reg_date}</td>
			<td align="center">${dto.readcount}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
</div>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<%@ include file="../include/footer.jsp" %>
