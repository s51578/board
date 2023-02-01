$(function() {
	'use strict';
	window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
					alert("필수 항목을 채워야합니다")
					
					$(".form-control:invalid").first().focus();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);

	function sortRegImage(targetTable, paramName){
		
		$(targetTable).find(".tr__caret--div").each(function(i, e){

			$(e).find("._reg_image_param").attr("name", paramName + "["+i+"].no");
			$(e).find("._reg_image_cpt").attr("name", paramName + "["+i+"].caption");
			
		})
		
	}
	
	$(".input__file__add--btn").click(function(e){
		
		var element = e.target;
		var table = $(element).attr("for");
		var template = $(element).attr("template");
		var paramName = $(table).attr("paramName");

		var template_html = $(template).find("tbody").html();
		$(table).append(template_html);

		sortRegImage(table, paramName);
		
	})
	
	$(".input__one__file__remove--btn").click(function(e){
		
		var index = $(e.target).attr("index");
		$("#mtImagePreview"+index).attr("src", "");
		$(".custom-file-label[for=mtImage"+index+"]").text("Choose file");
		$("#mtImageNo"+index).val("");
		$("#mtImageAdd" + index).val("");
	})
	
	$(document).on('click', '.input__file__remove--btn', function(e){
		
		var tr = $(e.target).parents("tr");
		var table = $(tr).parents("table");
		var paramName = $(table).attr("paramName");
			
		$(tr).remove();
		
		sortRegImage(table, paramName);
	})
	
	/*$(".input__file__change--up").click(function(e){*/
	
	$(document).on('click', '.input__file__change--up', function(e){
		
		var div = $(e.target).parents(".tr__caret--div");
		var table = $(e.target).parents("table");
		var paramName = $(table).attr("paramName");
		var prev = $(div).prev();
		
		if( $(prev).hasClass("tr__caret--div") ) {
			$(prev).insertAfter(div);
		}
		
		sortRegImage(table, paramName);
		
	})
	
	/*$(".input__file__change--down").click(function(e){*/
	$(document).on('click', '.input__file__change--down', function(e){
		
		var div = $(e.target).parents(".tr__caret--div");
		var table = $(e.target).parents("table");
		var paramName = $(table).attr("paramName");
		var next = $(div).next();
		
		if( $(next).hasClass("tr__caret--div") ) {
			$(next).insertBefore(div);
		}
		
		sortRegImage(table, paramName);
		
	})
	
	//시공 가능 공간 체크박스
	$("#allPlaceCheck").on("change", function(){
		
		if(this.checked)
			$(".place_check").prop("checked", true);
		else
			$(".place_check").prop("checked", false);
	});
	
	// 색상추출 
	$("#colorThief_btn").on("click", function(){
		
		var no = $("#mainMaterialImageNo").val();
		
		if(typeof no =='undefined' || no == null || no == "") {
			alert("추출할 사진이 없습니다");
			return false;
		}
		
		const colorThief = new ColorThief();
		//const color = colorThief.getColor($('#sampleImg')[0]);
		//const img = document.getElementsByClassName("img-thumbnail");
		var img = document.getElementById("mainMaterialImagePreview");
		
		const color = colorThief.getColor(img);
 		document.querySelector("#mainColor").style.backgroundColor='rgb('+color+')';
		$("#mainColor").css("display", "block");
 			
 		//var colors = colorThief.getPalette($('#sampleImg')[0], 4);
 		var colors = colorThief.getPalette(img, 4);
 		
		for(var i=0; i<colors.length; i++) {
   			$("#ColorSector_"+(i+1)).css("background-color", "rgb("+colors[i]+")").css("display", "block");
 		}
		
		function convertHex(color) {
			var hex = color.toString(16);
			
			return hex.length == 1 ? "0" + hex : hex;
		}
		
		function rgbToHex(red, green, blue) {
			return "#" + convertHex(red) + convertHex(green) + convertHex(blue);
		}
		
		var dominateColor = rgbToHex(color[0], color[1], color[2]);
		//console.log("Hex dominateColor : " + dominateColor);
		$("#DominateColor").val(dominateColor);
		
		var colorOne = rgbToHex(colors[0][0], colors[0][1], colors[0][2]);
		//console.log("Hex ColorOne : " + colorOne);
		$("#ColorOne").val(colorOne);
		
		var colorTwo = rgbToHex(colors[1][0], colors[1][1], colors[1][2]);
		//console.log("Hex colorTwo : " + colorTwo);
		$("#ColorTwo").val(colorTwo);
		
		/*var colorThree = rgbToHex(colors[2][0], colors[2][1], colors[2][2]);
		//console.log("Hex colorThree : " + colorThree);
		$("#ColorThree").val(colorThree);
		
		var colorFour = rgbToHex(colors[3][0], colors[3][1], colors[3][2]);
		//console.log("Hex colorFour : " + colorFour);
		$("#ColorFour").val(colorFour);*/
		
		//$("#colorThief_btn").hide();
	});
});
