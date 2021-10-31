   $(document).ready(function(){
      $(".answers").hide();
	  $(".up_btn").hide();
	  
	  $(".actC").on("click", function(){
		  $.ajax({
				url:"changeCategory",
				type:"GET",
				data: {"cno":$(this).attr("cno")},
				success:function(data){
					var result = JSON.parse(data);
					var cno = result['cno'];
					var faqObj = result['FaqObj'];
					var category = result['category'];
					$(".totalcounts").html("총<b>"+faqObj.length+"</b>개");
					
					$(".actC").attr("class", "actC");
					$(".actC[cno="+cno+"]").attr("class", "active actC");
					$("input[name='hiddencno']").attr("value", cno);
					console.log(faqObj.length);
					$(".myfaq").remove();
					if(faqObj.length == 0){
						$(".download").before("<ul class='myfaq'><li class='nodata'>조회된 데이터가 없습니다.</li></ul>");
					}else{
						for(var i = 0; i < faqObj.length; i++){
							var admin = "";
							if(result['UserPermit'] == 3){
								admin = "<div class='update_btn' style='cursor:pointer;' value='"+faqObj[i]['num']+"'>(수정/ </div><div class='delete_btn' style='cursor:pointer;' value='"+faqObj[i]['num']+"'>삭제)</div>"
							}
							var $obj = $("<ul class='myfaq'><li class='question' id='qu_bno'>" + (faqObj.length - i) +
						          	"</li><li class='question' id='qu_name'>"+
						          	admin+
						          	category[faqObj[i]['cno']]['name']+
						        	"</li><li class='hidden_btn' id='qu_qu' style='cursor:pointer;'>"+
						        	faqObj[i]['question']+
						        	"<input type='hidden' name='hiddenbno' id='hiddenbno' value="+faqObj[i]["num"]+"><input type='hidden' name='hiddenreadcount' id='hiddenreadcount' value='"+faqObj[i]['readcount']+"'><input type='hidden' name='hiddenanswer' id='hiddenanswer' value='" + faqObj[i]['answer'] +"'><div class='v_btn'>V</div><div class='up_btn'>Λ</div></li><li class='answers' class='an'><div class='showanswer'>"+
						        	faqObj[i]['answer']+"</div></li></ul>")
							$(".download").before($obj);
							
							$(".answers").hide();
							$(".up_btn").hide();
						}
					}
				}
			})
	  })
	  
    $(document).on("click","li[class='hidden_btn']", function(){
    	var readcountcno = $("input[name='hiddencno']").val();
    	var readcountbno = $(this).find("input[name='hiddenbno']").val();
			if($(this).find(".up_btn").css("display") == "none"){
				$.ajax({
					url:"faqReadcount",
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
    	  if($(".myfaq").length < 15){
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
       	$(".search_btn").on("click", function(){
       		$.ajax({
				url:"searchFaq",
				type:"GET",
				data: {"cno":$("input[name='hiddencno']").attr("value"), "keyword": $("#searchContent").val()},
				success:function(data){
					var result = JSON.parse(data);
					var cno = result['cno'];
					var faqObj = result['FaqObj'];
					var category = result['category'];
					$(".totalcounts").html("총<b>"+faqObj.length+"</b>개");
					
					$(".actC").attr("class", "actC");
					$(".actC[cno="+cno+"]").attr("class", "active actC");
					
					$(".myfaq").remove();
					for(var i = 0; i < faqObj.length; i++){
						var admin = "";
						if(result['UserPermit'] == 3){
							admin = "<div class='update_btn' style='cursor:pointer;' value='"+faqObj[i]['num']+"'>(수정/ </div><div class='delete_btn' style='cursor:pointer;' value='"+faqObj[i]['num']+"'>삭제)</div>"
						}
						var $obj = $("<ul class='myfaq'><li class='question' id='qu_bno'>" + (faqObj.length - i) +
					          	"</li><li class='question' id='qu_name'>"+
					          	admin+
					          	category[faqObj[i]['cno']]['name']+
					        	"</li><li class='hidden_btn' id='qu_qu' style='cursor:pointer;'>"+
					        	faqObj[i]['question']+
					        	"<input type='hidden' name='hiddenbno' id='hiddenbno' value="+faqObj[i]["num"]+"><input type='hidden' name='hiddenreadcount' id='hiddenreadcount' value='"+faqObj[i]['readcount']+"'><input type='hidden' name='hiddenanswer' id='hiddenanswer' value='" + faqObj[i]['answer'] +"'><div class='v_btn'>V</div><div class='up_btn'>Λ</div></li><li class='answers' class='an'><div class='showanswer'>"+
					        	faqObj[i]['answer']+"</div></li></ul>")
						$(".download").before($obj);
						
						$(".answers").hide();
						$(".up_btn").hide();
					}
				}	
       		});
       	})
       	var formObj = $("form[name='form']");
		$(document).on("click", "div[class='delete_btn']", function(){
			var hidn = $("input[name='hiddenbno']");
			var hidbno=$(this).attr("value");
			formObj.attr("action", "deleteFaq");
			formObj.attr("method", "post");
			var hid = $("input[name='hiddencno']");
			var hidcno = $("input[name='hiddencno']").val();
			hid.attr("name","cno");
			hid.attr("value",hidcno);
			hidn.attr("name","bno");
			hidn.attr("value",hidbno);
			formObj.submit();
		})
		$(document).on("click", "div[class='update_btn']", function(){
			var hidn = $("input[name='hiddenbno']");
			var hidbno=$(this).attr("value");
			var hid = $("input[name='hiddencno']");
			var hidcno = $(this).siblings("#hiddencno").val();
			hid.attr("name","cno");
			hid.attr("value",hidcno);
			hidn.attr("name","bno");
			hidn.attr("value",hidbno)
			formObj.attr("action", "updateFaq");
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
			formAdd.attr("action" ,"insertFaq");
			formAdd.attr("method", "get");
			formAdd.submit();
		})
		
   $("#act_bt").on("click", function(){
		var cateCno = $("input[name='catecno']");
		var cateCnoV = $(this).find(".catecno").attr("value");
		var formca = $("form[name='cate']");
		formca.attr("action", "faqList");
		formca.attr("method", "get");
		cateCno.attr("name", "cno");
		cateCno.attr("value", cateCnoV);
		formca.submit();
		
	})
	
$(".act_B").on("click", function(){
      var ajcno = $(this).siblings(".ajacno").val();
        $(".aja").load("faqList?cno="+ajcno)
		$(this).parent().addClass("active");
		$(this).parent().siblings().removeClass("active");
   })



})




