<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div id="footer" style="background-color: white;">
		<div style="padding: 0 14%; position: relative;">
			<div style="font-weight: bold; font-size: 30px;position:absolute; ">
				<a href="javascript:preFooter();">〈</a>
			</div>
			<div style="left:86%; font-weight: bold; font-size: 30px;position:absolute; ">
				<a style="margin-left:-20px" href="javascript:nextFooter();">〉</a>
			</div>
			<div style="margin-left:2%;width:96%; height: 40px; font-size: 30px; overflow: hidden; white-space: nowrap;">
			<c:forEach var="footer" items="${footerContent}">
				<a style="margin:0 40px; transition: all ease 1s 0s;" id="footerBanner" href="${footer[1]}"><img alt="${footer[0]}" src="${pageContext.request.contextPath}/resources/images/logos/${footer[0]}.png"></a>
			</c:forEach>
			</div>
		</div>
		<hr>
		<div style="padding:0 14%; padding-bottom: 1.7%">
			<h3>EBS 온라인클론스</h3>
			<div style=" display: inline-flex; justify-content: space-between; width:100%;">
			<div style="float: left;" class="footer">
				<a href="https://sso.ebs.co.kr/policy/privacy?fsdc=" style="color:green">개인정보처리방침</a>
				| <a href="https://sso.ebs.co.kr/policy/termsInfo">이용약관/회원약관</a>
				| <a href="https://home.ebs.co.kr/copyright/main">저작권 침해 제보</a>
				| <a href="https://www.ebsoc.co.kr/onlineclass">온라인클론스 소개</a>
				| <a href="manual">이용 매뉴얼</a>
				<div style=" color:gray;">
				<br>(10393) 경기도 고양시 일산동구 한류월드로 281 한국교육방송공사<br>
				사업자등록번호 : 229-82-01343 (한국교육방송공사사장 김명중) 부가통신사업신고 10077 통신판매신고 : 고양일산동 1415호<br>
				<br>Copyright © EBS. All Rights Reserved.
				</div>
			</div>
			<div style="width: 30%" align="right">
				<span style="color:#565656; font-size: 30px">고객센터 1588-1580</span><br>
				<span style="color:gray;">월요일~금요일 8:00~18:00<br>
				토요일 9:00~18:00<br>
				(점심시간 12시~13시, 일요일 휴무)<br>
				hotline@ebs.co.kr</span>
			</div>
			</div>
		</div>
	</div>
	
<script 
	src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
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
</body>
</html>
