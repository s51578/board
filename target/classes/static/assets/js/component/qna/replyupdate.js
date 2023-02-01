$(function(){
	$("#regreplyUpdatebtn").click(function(){
		var contents = $("#replyContents").val();
		
		if(typeof contents ==='undefined' || contents === null || contents == "") {
			alert("내용을 입력해주세요.");
			$("#replyContents").focus();
			return false;
		}
		
	});
});