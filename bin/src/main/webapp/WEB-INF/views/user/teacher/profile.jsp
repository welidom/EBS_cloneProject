<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile.css" type="text/css">
<div style="padding: 0 14%;">
<div style="text-align: left;"><span style="font-weight: bold; font-size: 18px; color: darkgreen;">기본정보</span> <span>회원가입 시 입력한 기본정보를 확인합니다.</span></div>
<div class="basicInfo" style="border-top: 2px solid darkgreen; width:100%; margin-bottom: 30px;">
	<div>
		<span class="th" style="">아이디</span>
		<span class="td">${dto.id }</span>
		<span class="th">이름</span>
		<span class="td">${dto.name }</span>
	</div>
</div>
<div style="text-align: left;"><span style="font-weight: bold; font-size: 18px; color: darkgreen;">나의 학교정보</span> <span>온라인클론스 이용 시 필요한 부가정보를 입력합니다.입력한 정보는 클래스 가입 및 강좌 수강 시 참고됨니다.</span></div>
<div class="basicInfo" style="border-top: 2px solid darkgreen; width:100%; margin-bottom: 50px;">
	<div class="info">
		<span class="th">구분</span>
		<span class="td">
			교사
		</span>
	</div>
	<div class="info">
		<span class="th">학교</span>
		<span class="td" style="width: 70%">${school.name}
		<c:choose>
		<c:when test="${lecture eq null}">
			<a class="btn" href="javascript:openpopup();">학교찾기</a>
		</c:when>
		<c:otherwise>
			<a class="disabled">학교찾기</a>
		</c:otherwise>
		</c:choose>
		<input type="hidden" name="schoolId" id="schoolId" value="${dto.schoolId }">
		※ 학교찾기를 진행해주세요.
		</span>
	</div>
	<div class="info">
		<span class="th">나이</span><span class="td">${age }</span>
	</div>
<div style="color:darkgreen">※ ‘나의 학교정보’는 클래스 가입 시 영향을 줄 수 있습니다.</div>
<div style="color:darkgreen"> &nbsp; &nbsp;정확한 정보를 입력해 주세요.</div>
</div>
<span id="target"></span>
</div>
<script>
function check(){
	var form = document.getElementById("sendProfile");
	if(document.getElementById("schoolId").value == ""){
		alert("학교찾기를 진행해주세요");
	}else{
		form.submit();
	}
}
function openpopup(){
	var popup = window.open('getSchool','popup','width=500, height=500, left=0, top=0');
	var timer = setInterval(function() {   
	    if(popup.closed) {
	        clearInterval(timer);  
	        window.location.reload();
	    }  
	}, 1000); 
}
</script>
<%@ include file="../../include/footer.jsp"%>