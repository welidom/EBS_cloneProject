<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<hr color="lightgray">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css">
<div class="blank">
</div>
<div class="img_one" >
	<img src="https://static.ebsoc.co.kr/oc_main/img/logo.b9d54b4.png" align="left">
</div>
<div class="login">
	<img src="https://static.ebsoc.co.kr/oc_main/img/banner_img.f33725d.png" align="right" style="position:relative;top:60px;left:-50px;">
	<h5>*Internet Explorer 사용을 권장하지 않습니다. (화상수업이 불가합니다.)</h5>
	<h5>*로그인 하실 때 ID/PW 대소문자를 구분하여 입력해 주시기 바랍니다.</h5>
	<h5 style="color:blue">*Chrome 사용시 로그인이 안되거나 로딩이 길어질 경우 [Ctrl]+[Shift]+[R]을 클릭해주세요.</h5>
	<form action="login.do" method="post">
			<input id="id" name="id" class="form-control" type="text" placeholder="아이디" style="width:300px;height:40px;font-size:15px;border-radius:0.2em;position:relative;top:20px;"><br>
			<input id="password" name="pw" class="form-control" type="password" placeholder="비밀번호" style="width:300px;height:40px;font-size:15px;border-radius:0.2em;position:relative;top:5px;">
			<button type="submit" style="display:inline-block;width:140px;height:100px;font-size:20px;color:white;;font-weight:bold;background-color:#6464e6;border-radius:0.5em;padding:5px 20px;text-decoration: none;position:relative;top:-21px;left:10;border:0;outline:0;cursor:pointer;">로그인</button>
	</form>
	<div class="snslogo" style="display: flex; justify-content: space-between;">
		<p style="left:20px">SNS 로그인<span style="border-right-width:0.5px;border-right-style:solid;border-left-color:black;position:relative;left:25px;"></span></p>
		<span>
			<a href="" class="snsloginBtn" style="background-color:green;color:white">N</a>
			<a href="" class="snsloginBtn" style="background-color:#3232b4;color:white;">f</a>
			<a href="" class="snsloginBtn" style="background-color:yellow;color:brown">K</a>
			<a href="" class="snsloginBtn" style="background-color:sandybrown;color:white">G</a>
			<a href="" class="snsloginBtn" style="background-color:black;color:white;">A</a>
		</span>
	</div>
	<br><br>
	<hr style="max-width:97%">
	<h5 style="text-align:center">한번의 가입으로 EBS와 EBS 패밀리 사이트의 서비스를편리하게 이용하실 수 있습니다.</h5>
	<div style="text-align:center;display:flex;justify-content: center;">
		<button type="button" class="btn" onclick="location.href='signUp.do'">회원가입</button>
		<button type="button" class="btn">아이디찾기</button>
		<button type="button" class="btn">비밀번호 찾기</button>
	</div>
</div>
<script
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous">
</script>
<script>
function preFooter() {
	var margin = document.getElementById("footerBanner").style.marginLeft
	margin = parseInt(margin)
	if(margin < 40){
		margin += 200
	}
	document.getElementById("footerBanner").style.marginLeft = margin+"px";
}
function nextFooter() {
	var margin = document.getElementById("footerBanner").style.marginLeft
	margin = parseInt(margin)
	if(margin > -360){
		margin -= 200
	}
	document.getElementById("footerBanner").style.marginLeft = margin+"px";
}
</script>
<%@ include file="../include/footer.jsp" %>