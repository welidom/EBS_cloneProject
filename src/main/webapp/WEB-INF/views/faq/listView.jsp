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
	<div class="search">
		<input type="text" name="searchContent" id="searchContent" class="cancelenter" placeholder="검색어를 입력하세요."><button type="button" class="search_btn"style="cursor:pointer;">검색</button>
	</div>
   <ul class="tab">
   <c:set var="act" value="0"/>
   <c:forEach items="${listCategory}" var="category" >
   		<li id="act_bt"><input type="hidden" name="ajacno" class="ajacno" value="${act + 1000}">
   		<span class="act_B"  style="cursor:pointer;">${category}</span>
   		</li>
   		<c:set var="act" value="${act+1 }" />
   </c:forEach>
   </ul>
		<c:if test="${list ne null}">
		<c:if test="${UserId eq 3}">
       		<div class="writeQu" style="cursor:pointer;">항목 추가</div>
       	</c:if>
       	<c:set var="re" value="${fn:length(list)}"/>
        <div class="totalcounts">총<b><c:out value="${re }"/></b>개</div>
       	<c:forEach items="${list}" var="dto">
            <ul class="myfaq">
            <li class="question" id="qu_bno"><c:out value="${re}"/>
            <c:set var="re" value="${re - 1}" />
          	</li>
            <li class="question" id="qu_name">
            <c:if test="${UserId eq 3}">
            <div class="update_btn" style="cursor:pointer;" value="${dto.num}">(수정/ </div>
            <div class="delete_btn" style="cursor:pointer;" value="${dto.num}">삭제)</div>
            </c:if>
          	
            <c:out value="${dto.category}"/></li>
            <li class="hidden_btn" id="qu_qu" style="cursor:pointer;"><c:out value="${dto.question}"/>
            <input type="hidden" name="hiddenbno" id="hiddenbno" value="${dto.num}">
           	<input type="hidden" name="hiddenreadcount" id="hiddenreadcount" value="${dto.readcount}">
           	<input type="hidden" name="hiddenanswer" id="hiddenanswer" value="${dto.answer}">
            <div class="v_btn">V</div><div class="up_btn">Λ</div></li>

            <li class="answers" class="an"><div class="showanswer"><c:out value="${dto.answer}"/>
            </div>
            </li>
            </ul>
         </c:forEach>
      
	<div id="download">
	<a href="faqDownload.do" class="key_color"><img src="${pageContext.request.contextPath }/resources/images/faq/downloadImage.png" class="downima"><b>온라인클래스 자주묻는질문(FAQ) 다운로드</b></a>
	</div>
	     	<div class="loadMore">더보기 <b class=keyboard_down>∨</b></div>
      </c:if>
</div>
</div>
<%@include file="../include/footer.jsp" %>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/EBSfaq.js"/>'></script>