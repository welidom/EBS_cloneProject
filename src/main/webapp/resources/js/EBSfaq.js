   $(document).ready(function(){
      $(".answers").hide();
	  $(".up_btn").hide();
	
	  $(".actC").on("click", function(){
		  $.ajax({
				url:"changeCategory.do",
				type:"GET",
				data: {"cno":$(this).attr("cno")},
				success:function(data){
					console.log(data);
				}
			})
	  })
	  
	  
      $(".hidden_btn").on("click", function(){
	var readcountcno = $("input[name='hiddencno']").val();
	var readcountbno = $(this).find("input[name='hiddenbno']").val();
			if($(this).find(".up_btn").css("display") == "none"){
				$.ajax({
					url:"faqReadcount.do",
					type:"GET",
					data: {"cno":readcountcno, "bno":readcountbno},
					success:function(data){
						
					}
				})
				$(this).find(".up_btn").show();
				$(this).find(".v_btn").hide();
				    var row = $(this).closest('li');
        			var ple = row.next('li');
         			ple.toggle(300);
			} else if($(this).find(".up_btn").css("display") != "none"){
				$(this).find(".up_btn").hide();
				$(this).find(".v_btn").show();
				       var row = $(this).closest('li');
        				 var ple = row.next('li');
         				ple.toggle(300);	
			}
	      })
      $(".myfaq").hide();
      $(".myfaq").slice(0,15).show();
      $(function(){
    	  if($(".myfaq > .answers").length < 15){
    	  $(".loadMore").hide();
    	  }
    	$(".loadMore").click(function(e){
    		e.preventDefault();
    		$(".myfaq:hidden").slice(0,15).show();
    		if($(".myfaq:hidden").length==0){
    			$(".loadMore").hide();
    		}
    	 })  
      })
       	    var formObj = $("form[name='form']");
			var inputObj = $("input[name='searchContent']");
			var hid = $("input[name='hiddencno']");
			var hidcno = $("input[name='hiddencno']").val();
			var cate = $("input[name='catecno']");
			var catecno = $("input[name='catecno']").val();
       	$(".search_btn").on("click", function(){
			formObj.attr("action", "faqList.do");
			inputObj.attr("name", "keyword");
			hid.attr("name", "cno");
			hid.attr("value", hidcno);
			formObj.attr("method", "post");
			formObj.submit();
       	})

		$(".delete_btn").on("click", function(){
			var hidn = $("input[name='hiddenbno']");
			var hidbno=$(this).attr("value");
			formObj.attr("action", "faqDelete.do");
			formObj.attr("method", "post");
			var hid = $("input[name='hiddencno']");
			var hidcno = $("input[name='hiddencno']").val();
			hid.attr("name","cno");
			hid.attr("value",hidcno);
			hidn.attr("name","bno");
			hidn.attr("value",hidbno);
			formObj.submit();
		})
		
		$(".update_btn").on("click", function(){
			var hidn = $("input[name='hiddenbno']");
			var hidbno=$(this).attr("value");
			var hid = $("input[name='hiddencno']");
			var hidcno = $(this).siblings("#hiddencno").val();
			hid.attr("name","cno");
			hid.attr("value",hidcno);
			hidn.attr("name","bno");
			hidn.attr("value",hidbno)
			formObj.attr("action", "faqUpdate.do");
			formObj.attr("method", "post");
			formObj.submit();
		})
	
$(".cancelenter").keydown(function() {
    if (event.keyCode === 13) {
        event.preventDefault();
    }
});
		$(".writeQu").on("click",function(){
			var formAdd = $("form[name='addForm']");
			formAdd.attr("action" ,"faqNewqu.do");
			formAdd.attr("method", "get");
			formAdd.submit();
		})
		
   $("#act_bt").on("click", function(){
		var cateCno = $("input[name='catecno']");
		var cateCnoV = $(this).find(".catecno").attr("value");
		var formca = $("form[name='cate']");
		formca.attr("action", "faqList.do");
		formca.attr("method", "get");
		cateCno.attr("name", "cno");
		cateCno.attr("value", cateCnoV);
		formca.submit();
		
	})
	
$(".act_B").on("click", function(){
      var ajcno = $(this).siblings(".ajacno").val();
        $(".aja").load("faqList.do?cno="+ajcno)
		$(this).parent().addClass("active");
		$(this).parent().siblings().removeClass("active");
   })



})

	




  