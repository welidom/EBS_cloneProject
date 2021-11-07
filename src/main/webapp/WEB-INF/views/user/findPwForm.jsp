<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<div>아이디: <input id="userId" check=false></div>
<div><span style="color:red" id="idMsg"></span></div>
<div>이메일: <input id="email"></div>
<span id="find" style="cursor: pointer;">찾기</span>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#userId").blur(function(){
		$.ajax({
			url:"checkUserOverlab",
			type:"GET",
			data:{"userId":$("#userId").val()},
			success:function(data){
				if(data){
					$("#idMsg").text("");
					$("#userId").attr("check", true);
				}else{
					$("#idMsg").text("존재하지 않는 아이디");
					$("#userId").attr("check", false);
				}
			}
		})
	})
	$("#find").on("click", function(){
		if($("#userId").attr("check") == 'false'){
			alert("아이디를 확인해주세요");
			$("#userId").focus();
		}else if(!$("#email").val()){
			alert("이메일을 입력해주세요");
			$("#email").focus();
		}else{
			$.ajax({
				url:"findPw",
				type: "POST",
				data:{"userId":$("#userId").val(), "email":$("#email").val()},
				success:function(data){
					if(data){
						alert("이메일이 전송되었습니다");
						window.close();
					}else{
						alert("잘못된 이메일입니다.")
					}
				}
			})
		}
	})
})
</script>
