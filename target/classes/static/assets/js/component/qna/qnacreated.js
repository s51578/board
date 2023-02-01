$(function(){
	$("#regbtn").click(function(){
		var email = $("#userEmail").val();
		var writer = $("#userName").val();
		var pwd = $("#userPwd").val();
		var title = $("#qnaTitle").val();
		var contents = $("#qnaContents").val();
		
		if(typeof email ==='undefined' || email === null || email == "") {
			alert("이메일을 입력해주세요.");
			$("#userEmail").focus();
			return false;
		}
		
		/*if(typeof writer ==='undefined' || writer === null || writer == "") {
			alert("작성자명을 입력해주세요.");
			$("#userName").focus();
			return false;
		}*/
		
		if(typeof pwd ==='undefined' || pwd === null || pwd == "") {
			alert("비밀번호를 입력해주세요.");
			$("#userPwd").focus();
			return false;
		}
		
		if(typeof title ==='undefined' || title === null || title == "") {
			alert("제목을 입력해주세요.");
			$("#qnaTitle").focus();
			return false;
		}
		
		if(typeof contents ==='undefined' || contents === null || contents == "") {
			alert("내용을 입력해주세요.");
			$("#qnaContents").focus();
			return false;
		}
	});
});