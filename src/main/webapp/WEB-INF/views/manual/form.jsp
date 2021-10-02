<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%request.setCharacterEncoding("UTF-8"); %>
<div style="padding: 0 14%">
<form action="insertManualPro.do" name="insertManual" method="post" enctype="multipart/form-data">
	<table>
	<tr>
		<th>메뉴얼명</th><td><input type="text" name="subject"></td>
	</tr>
	<tr>
		<th id="contentName">첨부파일</th><td id="content"><input name="content" type="file"></td>
	</tr>
	<tr>
		<th>구분</th><td>
		<select name="category">
			<option value="전체">전체</option>
			<option value="학생">학생</option>
			<option value="교사">교사</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>유형</th><td>
		<input type="radio" name="type" id="문서" value="1" onclick="doc();" checked>
		<label for="문서">문서</label>
		<input type="radio" name="type" id="동영상" value="0" onclick="video();">
		<label for="동영상">동영상</label>
		</td>
	</tr>
	<tr align="right">
		<td colspan="2"><input type="reset" value="다시작성" onclick="doc()">
		<input type="button" value="추가" onclick="check()"></td>
	</tr>
	</table>
</form>
</div>
<%@include file="../include/footer.jsp" %>

<script>
function doc() {
	document.getElementById('contentName').innerHTML  = '첨부파일';
	document.getElementById('content').innerHTML = '<input name="content" type="file">';
}
function video() {
	document.getElementById('contentName').innerHTML  = '동영상 url';
	document.getElementById('content').innerHTML = '<input type="url" name="content">';
}
function check(){
	var form = document.insertManual;
	if(form.subject.value==""){
		alert("메뉴얼명을 입력하세요")
		return form.subject.focus();
	}else if(!form.content.value){
		alert("첨부파일, 동영상URL을 추가해주세요")
		return;
	}else{
		form.submit();
	}
		
}
</script>