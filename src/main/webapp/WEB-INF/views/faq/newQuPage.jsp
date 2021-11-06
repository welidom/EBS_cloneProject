<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<style type="text/css">
.newQuForm{
margin-left: 20%;
margin-right: 20%;
margin-top : 10%;
}
</style>
<link rel="stylesheet" href="<c:url value='/resources/css/newqu.css'/>">
<%@include file="../include/header.jsp" %>
<hr color="lightgray">
<form name="writeform">
<div class="newContainer">
<table class="newQuForm">
	<tr>
	<th class="qu_title"><img src="${pageContext.request.contextPath }/resources/images/faq/qu.png">새 질문 추가</th>
	</tr>
	<tr>
		<td class="keyva">카테고리 명</td>
		<td>
		<select name="category" id="category">
			<optgroup label="카테고리">
			<option value="1">회원</option>
			<option value="2">클래스 이용</option>
			<option value="3">학습</option>
			<option value="4">클래스 개설/관리</option>
			<option value="5">학습 관리</option>
			<option value="6">화상 수업</option>
			<option value="7">강좌 관리</option>
			<option value="8">기타 </option>
			</optgroup>
		</select>
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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/newqu.js"/>'></script>
<%@include file="../include/footer.jsp" %> 