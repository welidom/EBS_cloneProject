<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri= "http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/header.jsp" %>
<link rel="stylesheet" href="<c:url value='/resources/css/EBSfaq.css'/>">
<div class="aja">
<div class="whereami">
	<div style="padding: 30px 14%;">
		<div style="color: white; margin-bottom: 20px;">Home 〉 FAQ</div>
		<div>
		<div style="font-weight: bold; font-size: 25px; color: white;">FAQ</div>
		<div style="color:white;">온라인 클래스 이용시 자주 묻는 질문과 답변을 안내합니다.</div>
		</div>
	</div>
</div>
<div class="container">
<form name="addForm">
</form>
<form name="form">
	<div class="search">
		<input type="text" name="searchContent" id="searchContent" class="cancelenter" placeholder="검색어를 입력하세요."><button type="button" class="search_btn"style="cursor:pointer;">검색</button>
		<input type="hidden" name="hiddencno" id="hiddencno" value="${cno}">
	</div>
   <ul class="tab">
   <c:set var="act" value="0"/>
   <c:forEach items="${listCategory}" var="category" >
   		<li id="act_bt">
   		<c:choose>
   			<c:when test="${cno eq act}">
   				<a class="active actC" cno="${act }" style="cursor:pointer;">${category}</a>
   			</c:when>
   			<c:otherwise>
   				<a class="actC" cno="${act}" style="cursor:pointer;">${category}</a>
   			</c:otherwise>
   		</c:choose>
   		</li>
   		<c:set var="act" value="${act+1 }" />
   </c:forEach>
   </ul>
		<c:if test="${list ne null}">
		<c:if test="${UserId eq 3}">
       		<div class="writeQu" style="cursor:pointer;">항목 추가</div>
       	</c:if>
<div class="totalcounts">총<b>0</b>개</div>
	<div id="download" class="download">
	<a href="faqDownload.do" class="key_color"><img src="${pageContext.request.contextPath }/resources/images/faq/downloadImage.png" class="downima"><b>온라인클래스 자주묻는질문(FAQ) 다운로드</b></a>
	</div>
	     	<div class="loadMore">더보기 <b class=keyboard_down>∨</b></div>
      </c:if>
</form>
</div>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/EBSfaq.js"/>'></script>
<script>
var cno = ${cno}
$(document).ready(function(){
	$("[cno='"+cno+"']").click();
});
</script>
<%@include file="../include/footer.jsp" %>