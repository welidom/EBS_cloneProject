<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notice.css" type="text/css">
<div style="background-color: #2580EB; padding: 0 14%; color:white; display: inline-block; width: 72%">
	<span style="float: left; padding-top: 30px">
	HOME 〉 공지사항 〉 공지사항 추가<br>
	<br>
	<b style="font-size: 25px;">공지사항 추가</b><br>
	EBS온라인클레스와 교육부의 새로운 소식, 시스템 점검 동을 추가합니다.<br>
	</span>
	<img style="float: right;" alt="manual.png" src="${pageContext.request.contextPath}/resources/images/manual.png">
</div>
<div style="padding: 50px 14%;">
	<form action="insertNotice.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
	<table>
	<tr>
		<th>작성자:</th>
		<td><input id="category" name="category" maxlength="6" placeholder="6글자 제한"></td>
		<th>필독 </th><td><input type="checkbox" id="mustRead" name="mustRead" value="1"></td>
	</tr>
	<tr>
		<th>제목:</th><td colspan="3"><input id="subject" name="subject" style="width: 100%"></td>
	</tr>
	<tr>
		<th>내용:</th>
		<td colspan="3"><textarea id="content" name="content" rows="20px" cols="100%"></textarea> </td>
	</tr>
	<tr>
		<th>첨부파일:</th>
		<td><input type="file" id="file" name="file"></td>
		<td colspan="2" align="right"><input type="reset"><input type="submit" value="추가"></td>
	</tr>
	</table>
	</form>
</div>
<%@ include file="../include/footer.jsp" %>
