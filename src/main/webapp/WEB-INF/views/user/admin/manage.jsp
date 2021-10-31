<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<div style="padding: auto auto;">
<h1 style="text-align: center;">관리 페이지</h1>
<div style="width: 50%; margin: 0 auto;border: 1px dashed; margin-bottom: 50px;">
<span style="font-weight: bold; font-size: 20px; border: 1px dotted; width: 100%; display: inline-block; text-align: center;" id="manageUser">회원관리</span>
<ul id="usersBoard">
	<c:forEach items="${userList}" var="user">
	<li style="margin-bottom: 30px" id="user">이름: ${user.name}<span style="float: right;"><a class="deleteUser" value="${user.id }" style="color: red;cursor: pointer;">(계정 삭제)</a></span>
	<ul><li>생년월일: ${user.birth} 구분:
	<c:if test="${user.permit eq 1}">
		학생 
	</c:if>
	<c:if test="${user.permit eq 2}">
		교사
	</c:if>
		<c:set var="check" value="0"/>
	소속: <c:forEach items="${schoolList }" var="school">
		<c:if test="${user.schoolId eq school.id}">
			<c:set var="check" value="1"/>
			${school.name }
		</c:if>
	</c:forEach>
	<c:if test="${check eq 0}">
		없음
	</c:if>
	</li></ul></li>
	</c:forEach>
</ul>
</div>
<div style="width: 50%; margin: 0 auto;border: 1px dashed; margin-bottom: 50px;">
<span style="font-weight: bold; font-size: 20px; border: 1px dotted; width: 100%; display: inline-block; text-align: center;">학교관리<span style="float: right;font-weight: normal;font-size: 20px">추가</span></span>
<ul>
	<c:forEach items="${schoolList}" var="school">
	<li style="margin-bottom: 30px">${school.name}<span style="float: right;"><a>삭제</a>/<a>수정</a></span>
	<ul><li>아이디: ${school.id} 비밀번호: ${school.pw }
	<c:set var="check" value="0"/>
	관리자: <c:forEach items="${userList }" var="user">
		<c:if test="${school.manager eq user.id}">
		<c:set var="check" value="1"/>
			${user.name}
		</c:if>
	</c:forEach>
	<c:if test="${check eq 0}">
		없음
	</c:if>
	</li></ul></li>
	</c:forEach>
</ul>
</div>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/manage.js"/>'></script>
<%@include file="../../include/footer.jsp"%>