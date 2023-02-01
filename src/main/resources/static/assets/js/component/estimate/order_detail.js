$(function(){
	$("#emailbtn").on("click", function(){
		$("#emailModal").modal();
	});
	
	
});

function send(address, orderNo) {
	
	var formData = new FormData();
	var firstwrap = '<div style="width:700px;">';
	var lastwrap = '</div>';
	
	//var address = order.email;
	var form = document.getElementById("emailformatform"); // 전송할 div(html)
	var titleform = document.getElementById("emailformatTitle");
	
	/*form_child.style.borderTopLeftRadius = "30px";
	form_child.style.borderBottomLeftRadius = "30px";
	form_child.style.borderTopRightRadius = "30px";
	form_child.style.borderBottomRightRadius = "30px";
	form_child.style.backgroundColor = "#F7F8FC";*/
	
	var title = $(titleform).html();
	var contents = $(form).html();
	//var footer = $("footer").html();
	
	formData.append("orderNo", orderNo);
	formData.append("address", address);
	formData.append("contents", firstwrap + contents + lastwrap);
	
	$.ajax({
		url : "/service/estimate/send/email",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			//console.log(data.status);
		},
		error : function(data, textStatus, xhr) {

			if (data.status == 200)
				alert("로그인이 필요합니다.");
			else if (data.status == 503)
				alert("이미 등록되었습니다." + textStatus);
			else if (data.status == 400)
				alert("잘못된 요청입니다." + textStatus);
			else
				alert("이메일 전송에 실패하였습니다." + textStatus);
		}
	});
	
}