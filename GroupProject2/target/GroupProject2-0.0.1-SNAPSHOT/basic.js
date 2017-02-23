$(document).ready(function(){
	
	//--- SEARCH BY IMSI ---//
	$("#searchByImsiButton").click(function(){
		
		var imsi = $("#imsi").val();
		
		$.ajax({
	
			type:"GET",
			url:"rest/base/"+imsi,
			dataType:"json",
			
			success: function(data) {
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append("<li>"+value.imsi+"</li>");
				});	
			}

		});
		
	});	
	
});	