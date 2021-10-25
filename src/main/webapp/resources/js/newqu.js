$(document).ready(function(){
	$(".write_btn").on("click", function(e){
	if($("#question").val().length == 0){
		alert('질문을 입력해주세요.');
		e.preventdefault();
	} else if($("#answer").val().length ==0){
		alert("답변을 입력해주세요.")
		e.preventdefault();
	} else{		
	var formObj = $("form[name='writeform']");
	formObj.attr("action", "insertFaq.do");
	formObj.attr("method", "post");
	formObj.submit();	
	}
	})
	
	
	$(".cancel_btn").on("click", function(){
		location.href= 'faqList.do?cno=0';
	})
	
	
})