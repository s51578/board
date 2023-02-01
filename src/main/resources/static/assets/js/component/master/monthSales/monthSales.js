function updateRank(monthNo, targetrank) {
	
	if(targetrank < 1)
		return;
	
	var formData = new FormData();
	
	formData.append("monthNo", monthNo);
	formData.append("targetrank", targetrank);
	
	$.ajax({
		url : "/ajax/update/monthSales",
		type : "POST",
		cache : false,
		async : false,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			location.href = "/master/contents/monthSales/list";
		},
		error : function(data, textStatus, xhr) {

			if(data.status > 200)
				alert("불편을 드려서 죄송합니다." + textStatus);
			
		}
	});
	
}