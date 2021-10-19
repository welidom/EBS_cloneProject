<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri= "http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/notice.css" type="text/css">
<div style="background-color: #2580EB; padding: 0 14%; color:white; display: inline-block; width: 72%">
	<span style="float: left; padding-top: 30px">
	HOME 〉 공지사항 〉 삭제<br>
	<br>
	<b style="font-size: 25px;">공지사항 삭제</b><br>
	EBS온라인클레스와 교육부의 새로운 소식, 시스템 점검 동을 안내합니다.<br>
	</span>
	<img style="float: right;" alt="manual.png" src="${pageContext.request.contextPath}/resources/images/manual.png">
</div>
<div style="padding: 40px 14% 20px 14%;">
	<form action="deleteNotice.do" name= "deleteNums" method="post">
	<div style="font-weight: bold;">
		총<span id="checkedCount" style="color:#2580EB">0</span>개
		<span style="float: right;">
		<a href="javascript:submitNums()" style="float:right; color:black; background-color: lightgray; border: 1px hidden; padding:10px; border-radius: 40px; font-weight: bold;">공지사항 삭제 〉</a>
		<a href="notice.do" style="float:right; color:white; background-color: #2580EB; border: 1px solid; padding:10px; border-radius: 40px; font-weight: bold;">공지사항 취소  〉</a>
		</span>
	</div>
	<table style="border-top: 2px solid #2580EB; width: 100%" class="notice">
	<tr style="background-color: ; border-top: 2px solid #2580EB;">
		<th style="width: 5%">선택</th>
		<th style="padding : 15px 0; width: 10%;">구분</th>
		<th>제목</th>
		<th style="width: 10%;">첨부파일</th>
		<th style="width: 10%;">등록일</th>
		<th style="width: 10%;">조회수</th>
	</tr>
	<c:forEach items="${mustList}" var="dto">
	<tr align="center">
	<c:set var="color" value="black"/>
		<td><input type="checkbox" value="${dto.num }" name="nums" onclick="onClick()"></td>
		<td style="padding: 15px 0;color:#2580EB;">${dto.category}</td>
		<td align="left" style="color:#2580EB;">${dto.subject}</td>
		<c:choose>
			<c:when test="${dto.attach ne null}"><td align="center"><a href="/filepath/noticeFiles/${dto.attach}" style="font-size: 20px; color:black;"><i class="fas fa-paperclip"></i></a></td></c:when>
			<c:otherwise><td></td></c:otherwise>
		</c:choose>
		<td align="center">${dto.reg_date}</td>
		<td align="center">${dto.readcount}</td>
	</tr>
	</c:forEach>
	<c:forEach items="${notMustList}" var="dto">
	<tr align="center">
		<td><input type="checkbox" value="${dto.num }" name="nums" onclick="onClick()"></td>
			<td style="padding: 15px 0;color:black;">${dto.category}</td>
		<td align="left" >${dto.subject}</td>
		<c:choose>
			<c:when test="${dto.attach ne null}"><td align="center"><a href="/filepath/noticeFiles/${dto.attach}" style="font-size: 20px; color:black;"><i class="fas fa-paperclip"></i></a></td></c:when>
			<c:otherwise><td></td></c:otherwise>
		</c:choose>
		<td align="center">${dto.reg_date}</td>
		<td align="center">${dto.readcount}</td>
	</tr>
	</c:forEach>
	</table>
	</form>
</div>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
function submitNums() {
	document.deleteNums.submit();
}
function onClick() {
	document.getElementById('checkedCount').innerHTML= '<span id="checkedCount" style="color:#2580EB">'+
		$('input:checkbox[name=nums]:checked').length+"</span>";
}
</script>
<%@ include file="../include/footer.jsp" %>