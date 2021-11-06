<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
학교이름: <input name="schoolName"><br>
<span id="schoolMsg" style="color: red;"></span><br>
학교 관리자 ID: <input name="schoolManager"><br>
<span id="managerMsg" style="color: red;"></span><br>
<button id="scan">추가</button>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
				if(data == true){
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
									url:"insertSchool",
									type:"POST",
									data:{"schoolName":$("input[name='schoolName']").val(), "schoolManager":$("input[name='schoolManager']").val()},
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