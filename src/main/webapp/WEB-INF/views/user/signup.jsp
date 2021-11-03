<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="include/header.jsp" %>
<link rel="stylesheet" href="<c:url value='/resources/css/signup.css'/>">
	<h2 style="position:relative;left:20%;">>정보입력 및 본인확인</h2>
	<form name="signup">
	<table style="position:relative;left:20%;width:60%;height:100px;">
	<colgroup>
		<col width="250">
		<col width="*">
	</colgroup>
	<tbody>
	<tr>
		<th>아이디</th>
		<td><input id="id" name="id" class="inp" type="text" minlength="6" maxlength="12" check=false><a class="btn checkOverlap">중복확인</a>
			<br><span id="idNotice"></span>
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input id="pw" name="pw" class="inp" type="password" minlength="6" maxlength="12" check=false>
			<br><span id="pwNotice"></span>
		</td>
	</tr>
	<tr>
		<th>비밀번호 확인</th>
		<td><input id="pwCheck" name="user_pwcheck" class="inp" type="password" minlength="8" maxlength="12" check=false>
			<br><span id="pwCheckNotice"></span>
		</td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input id="name" name="name" class="inp" type="text" check=false>
			<br><span id="nameNotice"></span>
		</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>
		<input type="hidden" name="gender"/>
		<input id="male" name="user_gender" type="radio" value="1">남성 
		<input id="female" name="user_gender" type="radio" value="2">여성<br>
		<input id="agree_gender" name="sy" type="checkbox">선택정보(성별) 수집 및 이용 동의
			<table style="word-break:keep-all;width:100%;">
			<colgroup>
				<col width="42%">
				<col width="26%">
				<col width="42%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><strong>수집목적</strong></th>
					<th scope="col"><strong>수집항목</strong></th>
					<th scope="col"><strong>보유기간</strong></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>이용자 식별 및 본인 여부 확인</td>
					<td>성별</td>
					<td>회원탈퇴 시 까지</td>
				</tr>
			</tbody>
			</table>
			</td>
	</tr>
	<tr>
	<th>생년월일<input type="hidden" name="birth"></th>
	<td>
	<div style="float:left;">
		<select id="year" name="year" style="font-size:17px;padding:2px 15px 2px 2px;">
			<c:forEach var="value" begin="1900" end="2021" step="1">
				<option value="${value }">${value }</option>
			</c:forEach>
		</select>
		<label>년 </label>
	</div>
	<div style="float:left;">
		<select id="month" name="month"style="font-size:17px;padding:2px 15px 2px 2px;">
			<c:forEach var="value" begin="1" end="12" step="1">
				<option value="${value }">${value }</option>
			</c:forEach>
		</select>
		<label>월 </label> 
	</div>
	<div style="float:left;">
		<select id="date" name="date"style="font-size:17px;padding:2px 15px 2px 2px;">
			<c:forEach var="value" begin="1" end="31" step="1">
				<option value="${value }">${value }</option>
			</c:forEach>
		</select>
		<label>일</label> 
	</div>
	</tr>
	<tr>
		<th>이메일<input type="hidden" name="email"></th>
		<td><input id="email_name" class="inp" type="text" style="width:25%;"> @ <input id="email_address" class="inp" type="text" style="width:25%;"><p style="color:purple;font-size:15px;">아이디나 비밀번호를 찾기위해 사용되오니 정확한 정보로 입력하시기 바랍니다.</p></td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td><input id="phoneNum" name="phoneNum" type="text" class="inp"><br>
		<input id="agree_pnum" name="py" type="checkbox">선택정보(휴대폰) 수집 및 이용 동의
			<table style="word-break:keep-all;width:100%;">
			<colgroup>
				<col width="42%">
				<col width="26%">
				<col width="42%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><strong>수집목적</strong></th>
					<th scope="col"><strong>수집항목</strong></th>
					<th scope="col"><strong>보유기간</strong></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>이용자 식별 및 본인 여부 확인</td>
					<td>휴대전화번호</td>
					<td>회원탈퇴 시 까지</td>
				</tr>
			</tbody>
			</table>
			<p style="color:purple;font-size:15px;">아이디나 비밀번호를 찾기위해 사용되오니 정확한 정보로 입력하시기 바랍니다.</p>
			</td>
	</tr>
	</tbody>
	</table>
	</form>
	<div style="position:relative;left:20%;"><p>※ 교사 회원님을 위한 교사인증은 가입 후 회원정보수정에서 인증하실 수 있습니다.</p></div>
	<div style="text-align:center;display:flex;justify-content: center;">
			<button id="submit" class="button">확인</button>
	</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
</body>
</html>