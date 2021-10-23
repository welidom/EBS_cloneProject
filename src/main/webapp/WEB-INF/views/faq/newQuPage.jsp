<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<style type="text/css">
.newQuForm{
margin-left: 20%;
margin-right: 20%;
margin-top : 10%;
}
</style>
<script type="text/javascript" src='<c:url value="/resources/js/newqu.js"/>'></script>
<link rel="stylesheet" href="<c:url value='/resources/css/newqu.css'/>">
</head>
<body>
<%@include file="../include/header.jsp" %>
<form name="writeform">
<div class="newContainer">
<table class="newQuForm">
	<tr>
	<th class="qu_title"><img src="/resources/images/faq/qu.png">새 질문 추가</th>
	</tr>
	<tr>
		<td class="keyva">카테고리 명</td>
		<td>
		<select name="category" id="category">
			<optgroup label="카테고리">
			<option value="회원" value2="1001">회원</option>
			<option value="클래스 이용" value2="1002">클래스 이용</option>
			<option value="학습" value2="1003">학습</option>
			<option value="클래스 개설/관리" value2="1004">클래스 개설/관리</option>
			<option value="학습 관리" value2="1005">학습 관리</option>
			<option value="화상 수업" value2="1006">화상 수업</option>
			<option value="강좌 관리" value2="1007">강좌 관리</option>
			<option value="기타" value2="1008">기타 </option>
			</optgroup>
		</select>
	<input type="hidden" name="cno" id="cno">
	<input type="hidden" name="name" id="name" >
		</td>
	</tr>
	
	
	
	<tr>
		<td class="keyva">질문</td>
		<td><textarea name="question" id="question" rows="20" cols="50"  class="question"></textarea></td>
	</tr>
	<tr>
		<td class="keyva">답변</td>
		<td><textarea name="answer" id="answer" rows="20" cols="50" class="answer"></textarea></td>
	</tr>
	
</table>
		<div class="btns">
		<div class="write_btn" style="cursor:pointer;"><b>작성</b></div>
		<div class="cancel_btn" style="cursor:pointer">취소</div>
		</div>
</div>

</form>
<%@include file="../include/footer.jsp" %> 
</body>
</html>