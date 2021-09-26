<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<div style="padding: 0 14%">
<form action="">
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
		<input type="radio" name="type" id="동영상" value="1" onclick="video();">
		<label for="동영상">동영상</label>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="reset" value="다시작성"><input type="button" value="제출" onclick=""></td>
	</tr>
	</table>
</form>
</div>
<%@include file="/WEB-INF/views/include/footer.jsp" %>

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
	
}
</script>