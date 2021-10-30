<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../include/header.jsp"%>
<div style="padding: auto auto;">
<h1 style="text-align: center;">관리 페이지</h1>
<div style="width: 50%; margin: 0 auto;border: 1px dashed; margin-bottom: 50px;">
<span style="font-weight: bold; font-size: 20px; border: 1px dotted; width: 100%; display: inline-block; text-align: center;">회원관리</span>
<ul>
	<c:forEach items="${userList}" var="user">
	<li style="margin-bottom: 30px">이름: ${user.name}<span style="float: right;"><a href="#" onclick="deleteAccount(this);" style="color: red;" value="${user.id }">(계정 삭제)</a></span>
	<ul><li>생년월일: ${user.birth} 구분: 
	<c:if test="${user.permit eq 1}">
		학생 
	</c:if>
	<c:if test="${user.permit eq 2}">
		교사
	</c:if>
	소속: <c:forEach items="${schoolList }" var="school">
		<c:if test="${user.schoolId eq school.id}">
			${school.name }
		</c:if>
	</c:forEach>
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
	관리자: <c:forEach items="${userList }" var="user">
		<c:if test="${school.manager eq user.id}">
			${user.name}
		</c:if>
	</c:forEach>
	</li></ul></li>
	</c:forEach>
</ul>
</div>
</div>
<script>
function deleteAccount(obj){
	var popup = window.open('deleteAccount.do?id=','popup','width=500, height=250, left=1000, top=100');
	var timer = setInterval(function() {   
	    if(popup.closed) {
	        clearInterval(timer);  
	        window.location.reload();
	    }  
	}, 1000); 
}
</script>
<%@include file="../../include/footer.jsp"%>