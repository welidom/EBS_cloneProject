$(document).ready(function(){
	
	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var pattern_eng = /[a-zA-Z]/;
	
	$(".checkOverlap").on("click", function(){
		var UserId = $("#id").val();
		$.ajax({
			url:"checkOverlab.do?userId="+UserId,
			type:"GET",
			success:function(data){
				if(data == true && pattern_eng.test(UserId)){
					$("#idNotice").text("이미 사용중인 아이디입니다.");
					$("#idNotice").attr("style", "color:red;");
					$("#id").attr("check", false);
				}
				else if(data == false && UserId.length >= 6 && UserId.length <= 12 && pattern_eng.test(UserId)){
					$("#idNotice").text("사용가능한 아이디 입니다.");
					$("#idNotice").attr("style", "color:#8600cf;");
					$("#id").attr("check", true);
				}
				else{
					$("#idNotice").text("6~12자의 영문 대소문자와 숫자 사용");
					$("#idNotice").attr("style", "color:red;");
					$("#id").attr("check", false);
				}
			}
		})
	})
	$("#id").blur(function(){
		var UserId = $("#id").val();
		if(UserId.length < 6 || !(pattern_eng.test(UserId))){
			$("#idNotice").text("6~12자의 영문 대소문자와 숫자 사용");
			$("#idNotice").attr("style", "color:red;");
			$("#id").attr("check", false);
		}else{
			$("#idNotice").text("중복 확인을 해주세요.");
			$("#idNotice").attr("style", "color:red;");
			$("#id").attr("check", false);
		}
	})
	$("#pw").on("click",function(){
		$("#pwNotice").text("8~12자의 영문대소문자, 숫자, 특수문자 사용 가능.");
		$("#pwNotice").attr("style", "color:red;");
	})
	$("#pwCheck").on("click", function(){
		if($("#pw").val() == ""){
			$("#pwCheckNotice").text("비밀번호를 다시 확인해주세요");
			$("#pwCheckNotice").attr("style", "color:red;");
		}
	})
	$("#pwCheck").blur(function(){
		var pw = $("#pw").val();
		var pwCheck = $("#pwCheck").val();
		if(pw == pwCheck && pw != ""){
			$("#pwCheckNotice").text("");
			if(pw.length >= 8 && pw.length <= 12){
				$("#pw").attr("check", true);
			}
		}else{
			$("#pwCheckNotice").text("비밀번호가 일치하지 않습니다.");
			$("#pwCheckNotice").attr("style", "color:red;");
			$("#pw").attr("check", false);
		}
		if(pw == ""){
			$("#pwCheckNotice").text("비밀번호를 다시 확인해주세요");
			$("#pwCheckNotice").attr("style", "color:red;");
			$("#pw").attr("check", false);
		}
	})
	$("#name").on("click", function(){
		var name = $("#name").val();
		if(name == ""){
			$("#nameNotice").text("이름을 입력해주세요.");
			$("#nameNotice").attr("style", "color:red;");
		}
	})
	$("#name").blur(function(){
		var name = $("#name").val();
		if(name == ""){
			$("#nameNotice").text("이름을 입력해주세요.");
			$("#nameNotice").attr("style", "color:red;");
			$("#name").attr("check", false);
		}else{
			$("#nameNotice").text("");
			$("#name").attr("check", true);
		}
	})
	$("#submit").on("click", function(){
		if($("#id").attr("check") == "false"){
			alert("아이디를 확인해주세요");
		}else if($("#pw").attr("check") == "false"){
			alert("비밀번호를 확인해주세요");
		}else if($("#name").attr("check") == "false"){
			alert("이름을 확인해주세요");
		}else if($("#email_name").val() == "" || $("#email_address").val() == ""){
			alert("이메일을 확인해주세요");
		}else{
			var form = $("form[name='signup']");
			var email = $("input[name='email']");
			var gender = $("input[name='gender']");
			var birth = $("input[name='birth']");
			var pNum = $("input[name='phoneNum']")
			email.attr("value", $("#email_name").val()+"@"+$("#email_address").val());
			if($("input:checkbox[id='agree_gender']:checked").length == 0){
				gender.attr("value", "0");
			}else{
				gender.attr("value", $("input[name='user_gender']:checked").val())
			}
			if($("input:checkbox[id='agree_pnum']:checked").length == 0){
				pNum.attr("value", null);
			}
			birth.attr("value", $("#year").val()+"."+$("#month").val()+"."+$("#date").val());
			form.attr("action", "signUp.do");
			form.attr("method", "post");
			form.submit();
		}
	})
})