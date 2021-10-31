<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/notice.css" type="text/css">
<div style="background-color: #2580EB; padding: 0 14%; color:white; display: inline-block; width: 72%">
	<span style="float: left; padding-top: 30px">
	HOME 〉 공지사항<br>
	<br>
	<b style="font-size: 25px;">공지사항</b><br>
	EBS온라인클레스와 교육부의 새로운 소식, 시스템 점검 동을 안내합니다.<br>
	</span>
	<img style="float: right;" alt="manual.png" src="${pageContext.request.contextPath}/resources/images/manual.png">
</div>
<div style=" padding: 40px 14%; margin-bottom: 90px">
	<div style="font-weight: bold;padding: 20px 0; border-top :2px solid #2580EB; width: 100%;background-color: #efefef; display:inline-block ;border-bottom: 1px solid lightgray;">
		<c:if test="${dto.mustRead eq 1}">
			<span style="margin-left: 20px;color:#2580EB;"><i class="fas fa-volume-up"></i> 필독</span>
		</c:if>
		<span style="margin-left: 20px;">${dto.subject }</span>
		<div style="float: right; margin-right: 20px; font-weight: normal; color: #363636;">
			등록일  ${dto.reg_date } | 조회수 ${dto.readcount }
		</div>
	</div>
	<div style="margin: 30px 0;margin-left: 20px">
		${dto.content }
	</div>
	<div style="font-weight: bold;padding: 10px 0; border-top :1px solid lightgray; width: 100%;display:inline-block ;border-bottom: 1px solid lightgray;">
		<c:if test="${dto.attach ne null}">
			<a href="${pageContext.request.contextPath}/resources/Files/noticeFiles/${dto.attach}" style="font-size: 10px; #2580EB;" download><i class="fas fa-paperclip"></i>${dto.attach }</a>
		</c:if>
	</div>
	<div style="margin: 50px 0; float: right;">
		<a href="updateNotice.do?num=${dto.num }" class="btn_update">수정</a>
		<a href="notice.do" class="btn_list">목록</a>
	</div>
</div>
<%@ include file="../include/footer.jsp" %>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
