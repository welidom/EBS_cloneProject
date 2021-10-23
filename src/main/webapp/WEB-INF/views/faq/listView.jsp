<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@include file="../include/header.jsp" %>
<link rel="stylesheet" href="<c:url value='/resources/css/EBSfaq.css'/>">
<div class="aja">
<div class="whereami">
	<div class="content">
		<ul class = "hometext">
			<li class="homestitle">HOME</li>
			<li class="homes">FAQ</li>
		</ul>
		<h3 class="page_tit">FAQ</h3>
		<p class="page_txt">온라인 클래스 이용시 자주 묻는 질문과 답변을 안내합니다.</p>
	</div>
</div>
<div class="container">
			<form name="addForm">
			</form>
<form name="form">
	<div class="search">
		<input type="text" name="searchContent" id="searchContent" class="cancelenter" placeholder="검색어를 입력하세요."><button type="button" class="search_btn"style="cursor:pointer;">검색</button>
	</div>
   <ul class="tab">
   <c:forEach items="${dto}" var="listCategory" >
   		<li id="act_bt"><input type="hidden" name="ajacno" class="ajacno" value="${listCategory.cno}">
   		<span class="act_B"  style="cursor:pointer;">${listCategory.category}</span>
   		</li>
   </c:forEach>
   </ul>


		<c:if test="${list ne null}">
		<c:if test="${UserId eq 3}">
       		<div class="writeQu" style="cursor:pointer;">항목 추가</div>
       	</c:if>
        <div class="totalcounts">총<b><c:out value="${totalBnoCount}"/></b>개</div>
       	<c:forEach items="${list}" var="dto">
           	
            <ul class="myfaq">
            <li class="question" id="qu_bno"><c:out value="${readTitle.rNum}"/>
           </li>
            <li class="question" id="qu_name">
            <c:if test="${none != 1000}">
            <c:if test="${UserId eq 3}">
            <div class="update_btn" style="cursor:pointer;" value="${readTitle.bno}">(수정/ </div>
            <div class="delete_btn" style="cursor:pointer;" value="${readTitle.bno}">삭제)</div>
            </c:if>
            </c:if>
          
            <c:out value="${dto.category}"/></li>
            <li class="hidden_btn" id="qu_qu" style="cursor:pointer;"><c:out value="${readTitle.question}"/>
            <input type="hidden" name="hiddencno" id="hiddencno" value="${readTitle.cno}">
            <input type="hidden" name="hiddenbno" id="hiddenbno" value="${readTitle.bno}">
           	<input type="hidden" name="hiddenreadcount" id="hiddenreadcount" value="${readTitle.readcount}">
           	<input type="hidden" name="hiddenanswer" id="hiddenanswer" value="${readTitle.answer}">
            <div class="v_btn">V</div><div class="up_btn">Λ</div></li>

            <li class="answers" class="an"><div class="showanswer"><c:out value="${readTitle.answer}"/>
            </div>
            </li>
            </ul>
         </c:forEach>
      
	<div id="download">
	<a href="faqDownload.do" class="key_color"><img src="/resources/images/faq/downloadImage.png" class="downima"><b>온라인클래스 자주묻는질문(FAQ) 다운로드</b></a>
	</div>

	
	     	<div class="loadMore">더보기 <b class=keyboard_down>∨</b></div>
      </c:if>	
</form>
</div>
</div>
<%@include file="../include/footer.jsp" %>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/EBSfaq.js"/>'></script>