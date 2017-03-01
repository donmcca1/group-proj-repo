$(document).ready(function(){
	
	//--- SEARCH BY IMSI ---//
	$("#searchByImsiButton").click(function(){
		
		var imsi =$.trim($("#imsi").val());
		
		if(imsi){
			imsi = $("#imsi").val();
		} else {
			imsi = 0;
		}
		
		console.log("Imsi is: " + imsi);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/"+imsi,
			dataType:"json",
			
			success: function(data) {
				$("#responseHolder").empty();
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append
						("<li> Event Id: "+value.eventId+" Cause Code: "+value.causeCode+"</li>");
				});	
			}

		});
		
	});	
	
});	