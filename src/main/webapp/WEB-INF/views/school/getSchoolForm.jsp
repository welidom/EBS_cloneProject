<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<map name="popup" id="popup">
<ul>
	<li id="searchBar" style="list-style: none;">학교이름: <input name="schoolName"><button id="searchSchool">학교찾기</button></li>
</ul>
<area alt="close" shape="rect" coords="380, 457, 487, 492" href="#" onclick="window.close();">
</map>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getSchool.js"></script>
