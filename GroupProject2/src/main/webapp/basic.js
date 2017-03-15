$(document).ready(function(){
	
	$(document).ready(function(){
	    $("#searchByImsiButton").prop('disabled',true);
	    $("#imsi").keyup(function(){
	        $("#searchByImsiButton").prop('disabled', this.value == "" ? true : false);     
	    })
	});  
    
	//--- SEARCH BY IMSI ---//
	$("#searchByImsiButton").click(function(){	
		
			//-- retrieves IMSI from form --//
			imsi = $("#imsi").val();
	
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
	
	//--- SEARCH BY DATES ---//
	
	$("#searchByDatesButton").click(function(){
		
		//-- retrieve dates from forms --//
		startDate = $("#startDate").val();
		endDate = $("#endDate").val();
		
		window.alert("Start Date:" + startDate);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/"+startDate+"/"+endDate,
		    cache: false,
			dataType:"json",
			
			success: function(data) {

				$("#responseHolder").empty();
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append
						("<li> IMSI: "+value.imsi+"</li>");
				});	
			}

		});
		
	});	
	
});	