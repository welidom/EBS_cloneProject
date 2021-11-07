$(document).ready(function(){
	$(".deleteUser").on("click", function(){
		$.ajax({
			url: "deleteUser",
			type:"GET",
			data:{"id":$(this).attr("value")},
			success: function(data){
				window.location.reload();
			}
		})
	})
	$("#deleteSchool").on("click", function(){
		$.ajax({
			url:"deleteSchool",
			type:"GET",
			data:{"id":$(this).attr("value")},
			success: function(data){
				window.location.reload();
			}
		})
	})
})
function addSchool(){
	var popup = window.open('insertSchool','popup','width=400, height=200, left=0, top=0');
	var timer = setInterval(function() {   
	    if(popup.closed) {
	        clearInterval(timer);  
	        window.location.reload();
	    }  
	}, 1000); 
}
function updateSchool(schoolId){
	var popup = window.open('updateSchool?schoolId='+schoolId,'popup','width=400, height=200, left=0, top=0');
	var timer = setInterval(function() {   
	    if(popup.closed) {
	        clearInterval(timer);  
	        window.location.reload();
	    }  
	}, 1000); 
}