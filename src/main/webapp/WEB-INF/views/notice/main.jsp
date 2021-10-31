<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri= "http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notice.css" type="text/css">
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
		<div style="font-weight: bold;display: flex; justify-content:space-between;">
			<div>
				총<span style="color:#2580EB;">${fn:length(notMustList) + fn:length(mustList) }</span>개
				<c:if test="${UserPermit eq 3}">
				<a href="insertNotice" class="btn_noticePlus"></a>
				<a href="deleteNotice" class="btn_noticeSub"></a>
				</c:if>
			</div>
			<form action="notice" method="post">
			<div style="float: right; display: block;">
				<select id="searchFor" name="searchFor" class="select search">
					<option value="subject">제목</option>
					<option value="content">내용</option>
				</select>
				<input id="search" name="search" placeholder="검색어를 입력하세요." onfocus="this.value=''" class="searchBar search">
				<input type="submit" value="검색 " class="search btn_submitKey">
				<input type="button" onclick="location.href='notice'" value="검색 초기화" class="search btn_resetKey">
			</div>
			</form>
		</div>
		<table style="border-top: 2px solid #2580EB; width: 100%" class="notice">
		<tr style="background-color: ; border-top: 2px solid #2580EB;">
			<th style="width: 5%">번호</th>
			<th style="padding : 15px 0; width: 10%;">구분</th>
			<th>제목</th>
			<th style="width: 10%;">첨부파일</th>
			<th style="width: 10%;">등록일</th>
			<th style="width: 10%;">조회수</th>
		</tr>
		<c:forEach items="${mustList}" var="dto">
		<tr align="center">
		<c:set var="color" value="black"/>
			<td style="color:#2580EB;"><i class="fas fa-volume-up"></i> 필독</td>
			<td style="padding: 15px 0;color:#2580EB;">${dto.category}</td>
			<td align="left" ><a style="color:#2580EB;" href="noticeContent?num=${dto.num }&&no=${dto.mustRead}">${dto.subject}</a></td>
			<c:choose>
				<c:when test="${dto.attach ne null}"><td align="center"><a href="/filepath/noticeFiles/${dto.attach}" style="font-size: 20px; color:black;" download><i class="fas fa-paperclip"></i></a></td></c:when>
				<c:otherwise><td></td></c:otherwise>
			</c:choose>
			<td align="center">${dto.reg_date}</td>
			<td align="center">${dto.readcount}</td>
		</tr>
		</c:forEach>
		<c:set var="re" value="${fn:length(notMustList) }"/>
		<c:forEach items="${notMustList}" var="dto">
		<tr align="center">
			<td>${re }</td>
			<c:set var="re" value="${re - 1}"/>
			<td style="padding: 15px 0;color:black;">${dto.category}</td>
			<td align="left" ><a style="color:black;" href="noticeContent?num=${dto.num }&&no=${dto.mustRead}">${dto.subject}</a></td>
			<c:choose>
				<c:when test="${dto.attach ne null}"><td align="center"><a href="/filepath/noticeFiles/${dto.attach}" style="font-size: 20px; color:black;" download><i class="fas fa-paperclip"></i></a></td></c:when>
				<c:otherwise><td></td></c:otherwise>
			</c:choose>
			<td align="center">${dto.reg_date}</td>
			<td align="center">${dto.readcount}</td>
		</tr>
		</c:forEach>
		</table>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
