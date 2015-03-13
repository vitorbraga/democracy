$(document).ready(function() {

	$("#enviar").click(function() {

		var params = {
			'fileInputDTO.inputFile' : document.getElementById("uploadFile").files[0]
		}

		$.ajax({
					type : "get",
					async : false,
					url : "/conciliacao/teste"
				});
		$.ajax({
					type : "Post",
					async : false,
					url : "/conciliacao/getEDIFile",
					data: params
					
				});

	});

});
