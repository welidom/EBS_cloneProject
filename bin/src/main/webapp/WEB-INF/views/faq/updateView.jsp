<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/header.jsp" %>
<hr color="lightgray">
<link rel="stylesheet" href="<c:url value='/resources/css/updateQu.css'/>">
<meta charset="UTF-8">
<style type="text/css">
.newQuForm{
margin-left: 20%;
margin-right: 20%;
margin-top : 10%;
}
</style>
<form name="writeform">
<div class="newContainer">
	<input type="hidden" name="cno" id="cno"  value="${cno}">
	<input type="hidden" name="num" id="num" value="${update.num}">
	<table class="newQuForm">
	<tr>
	<th class="qu_title"><img src="${pageContext.request.contextPath }/resources/images/faq/qu.png">질문 수정</th>
	</tr>
	
	<tr>
		<td class="keyva">현재 카테고리<input type="hidden" value="${update.category }" name="category" id="category"></td>
		<td><input type="text" value="${listCategory[update.category]}" readonly="readonly"></td>
	<tr>
		<td class="keyva">질문</td>
		<td><textarea name="question" id="question" rows="20" cols="50"  class="question">${update.question}</textarea></td>
	</tr>
	<tr>
		<td class="keyva">답변</td>
		<td><textarea name="answer" id="answer" rows="20" cols="50" class="answer">${update.answer}</textarea></td>
	</tr>
	
</table>
		<div class="btns">
		<div class="update_btn" style="cursor:pointer;"><b>수정</b></div>
		<div class="cancel_btn" style="cursor:pointer">취소</div>
		</div>
</div>

</form>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/updateQu.js"/>'></script>
<%@include file="../include/footer.jsp" %> 