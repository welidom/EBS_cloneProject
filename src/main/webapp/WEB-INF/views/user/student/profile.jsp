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
	<div>
		<span class="th">교사인증</span>
		<span class="td" style="width: 700px"><a style="text-align: left;" class="btn" href="auth?email=${dto.email }">교사인증</a>※ 교사인증을 위한 이메일을 발송합니다.</span>
	</div>
</div>
<div style="text-align: left;"><span style="font-weight: bold; font-size: 18px; color: darkgreen;">나의 학교정보</span> <span>온라인클론스 이용 시 필요한 부가정보를 입력합니다.입력한 정보는 클래스 가입 및 강좌 수강 시 참고됨니다.</span></div>
<div class="basicInfo" style="border-top: 2px solid darkgreen; width:100%; margin-bottom: 50px;">
<form action="profileUpdate" method="post" id="sendProfile">
	<div class="info">
		<span class="th">구분</span>
		<span class="td">
			학생
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
		※ 학교찾기 진행후 정보를 수정할 수 있습니다.
		</span>
	</div>
	<div class="info">
		<span class="th">학년</span>
		<span class="td">${grade }</span>
	</div>
	<div class="info">
		<span class="th">반</span>
		<c:choose>
		<c:when test="${school eq null }">
		<span><input value="${dto.classNum }" name="classNum" disabled>반</span>
		</c:when>
		<c:otherwise>
		<span><input value="${dto.classNum }" name="classNum">반</span>
		</c:otherwise>
		</c:choose>
	</div>
	<div class="info">
		<span class="th">번</span>
		<c:choose>
		<c:when test="${school eq null }">
		<span><input value="${dto.num }" name="num" id="num" disabled>번</span>
		</c:when>
		<c:otherwise>
		<span><input value="${dto.num}" name="num" id="num">번</span>
		</c:otherwise>
		</c:choose>
	</div>
	<div class="info">
	<span class="th">알림 수신</span>
		<c:if test="${dto.notice eq 1}">
			<input name="notice" type="checkbox" class="sms" value="1" checked="checked">
		</c:if>
		<c:if test="${dto.notice eq 0}">
			<input name="notice" type="checkbox" class="sms" value="1">
		</c:if>
		온라인클론스 알림 수신 설정
	</div>
	<div class="info">
	<span class="th">SMS 동의</span>
	<span>
		<c:if test="${dto.sms eq 1}">
			<input name="sms" type="checkbox" class="sms" checked="checked" value="1">
		</c:if>
		<c:if test="${dto.sms eq 0}">
			<input name="sms" type="checkbox" class="sms" value="1">
		</c:if>
		선생님 피드백, 독려 등 학습 SMS 수신 동의
	</span>
	</div>
<input class="btn" type="button" onclick="check();" value="저장" style="float: right;">
</form>
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
	}else if(document.getElementById("num").value==""){
		alert("번호를 입력해주세요");
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