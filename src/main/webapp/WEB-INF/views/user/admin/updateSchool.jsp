<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
학교이름: <input name="schoolName" value="${dto.name }"><br>
<span id="schoolMsg" style="color: red;"></span><br>
학교 관리자 ID: <input name="schoolManager" value="${dto.manager}"><br>
<span id="managerMsg" style="color: red;"></span><br>
<input type="hidden" id="id" value="${dto.id }">
<button id="scan">수정</button>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#scan").on("click", function(){
		var school = false;
		var manager = false;
		$.ajax({
			url:"checkSchoolOverlab",
			type:"GET",
			data:{"schoolName":$("input[name='schoolName']").val()},
			success:function(data){
				if(data == true && $("input[name='schoolName']").val() != "${dto.name}"){
					$("#schoolMsg").text("이미 존재하는 학교입니다.");
				}else{
					$("#schoolMsg").text("");
					$.ajax({
						url:"checkUserOverlab",
						type:"GET",
						data:{"userId": $("input[name='schoolManager']").val()},
						success:function(data){
							if(data != true && $("input[name='schoolManager']").val() != ""){
								$("#managerMsg").text("존재하지 않는 사용자입니다.");
							}else{
								$("#managerMsg").text("");
								
								$.ajax({
									url:"updateSchool",
									type:"POST",
									data:{"name":$("input[name='schoolName']").val(), "manager":$("input[name='schoolManager']").val(), "id":$("#id").val()},
									success:function(data){
										window.close();
									}
								})
							}
						}
					})
				}
			}
		})
	})
})
</script>