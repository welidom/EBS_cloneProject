<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/manual.css" type="text/css">
<div style="background-color: #2580EB; padding: 0 14%; color:white; display: inline-block; width: 72%">
	<span style="float: left; padding-top: 30px">
	HOME 〉 이용자메뉴얼<br>
	<br>
	<b style="font-size: 25px;">이용자메뉴얼</b><br>
	EBS온라인클레스 이용 메뉴얼입니다.<br>
	매뉴얼은 온라인 클래스의 새로운 기능을 업데이트하고 있습니다.<br>
	</span>
	<img style="float: right;" alt="manual.png" src="${pageContext.request.contextPath}/resources/images/manual.png">
</div>
<div style="padding: 40px 14% 20px 14%;">
	<div>
		<span style="width: 100%"><b style="font-size: 25px">동영상 메뉴얼</b><a href="insertManual.do" style="float:right; color:white; background-color: #2580EB;border: 1px solid; padding:10px; border-radius: 40px; font-weight: bold;">메뉴얼 추가 〉</a></span>
		<table style="border-top: 2px solid #2580EB; width: 100%" class="manual">
		<tr style="background-color: ; border-top: 2px solid #2580EB;">
			<th style="padding : 15px 0; width: 15%;">구분</th>
			<th style="padding : 15px 0;">메뉴얼명</th>
			<th style="padding : 15px 0; width: 15%;">재생</th>
			<th style="padding : 15px 0; width: 15%;">등록일</th>
		</tr>
		<c:forEach items="${videoManual}" var="dto">
		<tr>
			<td align="center">${dto.category}</td>
			<td>${dto.subject}</td>
			<td align="center">${dto.content}</td>
			<td align="center">${dto.reg_date}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
	<div>
		<b style="font-size: 25px">문서 메뉴얼</b>
		<table style="border-top: 2px solid #2580EB; width: 100%" class="manual">
		<tr style="background-color: ; border-top: 2px solid #2580EB;">
			<th style="padding : 15px 0; width: 15%;">구분</th>
			<th style="padding : 15px 0;">메뉴얼명</th>
			<th style="padding : 15px 0; width: 15%;">재생</th>
			<th style="padding : 15px 0; width: 15%;">등록일</th>
		</tr>
		<c:forEach items="${documentManual}" var="dto">
		<tr>
			<td align="center">${dto.category}</td>
			<td>${dto.subject}</td>
			<td align="center">${dto.content}</td>
			<td align="center">${dto.reg_date}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
