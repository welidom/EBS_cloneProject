<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
이메일: <input id="email"><span id="find" style="cursor: pointer;">찾기</span>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#find").on("click", function(){
		if(!$("#email").val()){
			alert("이메일을 입력해주세요");
			$("#email").focus();
		}else{
			$.ajax({
				url:"findId",
				type: "POST",
				data:{"email":$("#email").val()},
				success:function(data){
					alert("이메일이 전송되었습니다");
					window.close();
				}
			})
		}
	})
})
</script>