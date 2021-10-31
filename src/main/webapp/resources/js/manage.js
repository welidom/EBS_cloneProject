$(document).ready(function(){
	$(".deleteUser").on("click", function(){
		$.ajax({
			url: "userDelete",
			type:"GET",
			data:{"id":$(this).attr("value")},
			success: function(data){
				var result = JSON.parse(data);
				var users = result['users'];
				
				$("#usersBoard").remove();
				$("#manageUser").after($("<ul id='usersBoard'></ul>"));
				for(var i = 0; i < users.length; i++){
					var $obj = $("<li style='margin-bottom: 30px' id='user'>이름: "+users[i]['name']+
							"<span style='float: right;'><a id='deleteUser' value='"+ users[i]['id']+
							"' style='color: red;cursor: pointer;'>(계정 삭제)</a></span>"+
							"<ul><li>생년월일: "+ users[i]['birth']+
							" 구분: "+ users[i]['permit']+
							" 소속: "+users[i]['school']+"</li></ul></li>");
				    $("#usersBoard").append($obj);
				}
			}
		})
	})
})