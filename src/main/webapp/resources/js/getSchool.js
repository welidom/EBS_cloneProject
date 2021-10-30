$(document).ready(function(){
	$("#searchSchool").on("click", function(){
		$.ajax({
			url:"searchSchool.do",
			type:"GET",
			data:{"searchFor":$("input[name='schoolName']").val()},
			success:function(data){
				var result = JSON.parse(data);
				var schools = result['schools'];
				$("#school").remove();
				for(var i = 0; i < schools.length; i++){
					var $obj = $("<li id='school'><a href='#' onclick='set(this);' value='"+schools[i]['id']+"'>"+schools[i]['name']+"</a></li>");
					$("#searchBar").after($obj);
				}
			}
		})
	})
})
function set(obj){
	$.ajax({
		url:"changeSchool.do",
		type:"GET",
		data:{"schoolId":$(obj).attr("value")},
		success:function(data){
			window.close();
		}
	})
}
