<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<div style="background-color:#2580EB;padding: 0px 14%; display: inline-block;">
		<div style="display:flex; flex-direction: row; width: 100%;">
			<div style="display:flex; flex-direction: column; padding-top:50px; padding-right: 5%; width: 100%">
			<b style="font-size:20px;color:#ACCFF7; ">세상에서 가장 빠른 등교</b>
			<b style="color:white;font-size:50px">EBS 온라인클론스</b>
			<p style="color:#A8CCF7; font-size:15px; margin-bottom: 20px;">언제ㆍ어디서나 등교할 수 있는 EBS온라인클론스에서 선생님과 친구들을 만나볼까요?</p><br>
			<b><a href="" class="gtsButton gts gts" >등교하기  〉</a></b>
			</div>
			<img style="width: 800px; height: 350px" alt="mainBanner" src="${pageContext.request.contextPath}/resources/images/mainBanner.png">
		</div>
	</div>
	<div class="ebsNoticeBanner">
		<b style="font-size:40px;">온라인 클론스 안내사항</b>
		<table style="width: 100%">
			<tr>
				<td style="width: 50%; vertical-align: top; height:50%;">
				<p class="banner" style= "width: 85%; height: 50%">
					<span>공지사항</span>
					<a class="moreInfo" href="https://www.ebsoc.co.kr/notice">더보기 〉</a>
					<br>
					<c:forEach var="dto" items="${listNotice}">
						<br><span style="font-size:15px; color:#525252; float: left;">ㆍ</span>
						<a href="" class="notice">
							<span class="moreNotice">${dto.subject}</span>
							<span class="moreNoticed">${dto.reg_date}</span>
						</a>
					</c:forEach>
				</p>
				</td>
				<td rowspan="2" align="right"><p class="banner" style=" height:100%;"><span style="float: left;">서비스 업데이트</span>
					<a class="moreInfo" href="">더보기 〉</a><br><br>
				<img style="width: 100%;" src="${pageContext.request.contextPath}/resources/images/ebs.jpeg" alt="advice">
				</p></td>
			</tr>
			<tr>
				<td style="width: 50%; height:50%;">
				<p class="banner" style="width: 85%; height: 50%; margin-bottom: 0%;  line-height: 80%">
					<span>화상수업 업데이트</span>
					<a class="moreInfo" href="">더보기 〉</a>
					<span style="font-size:13px; color:#525252;">
					<br>
					<br>
					${getNotice.content}
					</span>
				</p>
				</td>
			</tr>
		</table>
	</div>
	<div style="background-color: white; padding:70px 14%;">
		<b style="font-size:40px;">자주 묻는 질문</b>
		<br>
		<a class="moreInfo" href="">더보기 〉</a>
		<br><br>
		<c:forEach var="dto" items="${listFaq}">
		<a class="faq" style="" href="">
			<span class="faqc" style="">${dto.category}</span><span class="slice">|</span><span>${dto.subject}</span>
			<span class="moreInfo">〉</span>
		</a>
		</c:forEach>
	</div>
	<div style="background-color: #e9edef;padding:70px 14%;">
		<b style="font-size: 40px; margin-bottom: 40px; display: inline-block;">온라인 클래스를<br>
		스마트하게.</b>
		<div style="margin-bottom: 40px;">
		<b style="font-size: 40px; color:#0b73bc;">선생님이시라면</b><a class="lookMenual" style="background-color: #0b73bc; " href="manual.do">메뉴얼보기 〉</a>
		</div>
		<div style="display: flex; flex-direction: row; flex-wrap: wrap;justify-content: space-between;">
			<b class="tob01 menualObj"><br>클래스 개설·관리</b>
			<b class="tob02 menualObj"><br>강좌 등록·관리</b>
			<b class="tob03 menualObj"><br>화상수업 조·종례</b>
			<b class="tob04 menualObj"><br>학습자 이력관리</b>
			<b class="tob05 menualObj"><br>학습 독려</b>
		</div>
		<div style="margin: 80px 0 40px;">
		<b style="font-size: 40px; color:#ffc13d;">학생이라면</b><a class="lookMenual" style="background-color: #ffc13d; " href="manual.do">메뉴얼보기 〉</a>
		</div>
		<div style="display: flex; flex-direction: row; flex-wrap: wrap;justify-content: space-between;">
			<b class="sob01 menualObj"><br>클래스 가입</b>
			<b class="sob02 menualObj"><br>강좌 수강</b>
			<b class="sob03 menualObj"><br>화상수업 참여</b>
			<b class="sob04 menualObj"><br>학습이력 확인</b>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>