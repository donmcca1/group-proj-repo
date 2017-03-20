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
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date",
			data: { start: startDate, end: endDate },
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
	
	//--- SEARCH BY MODEL AND DATES ---//
		
		$("#searchByModelAndDatesButton").click(function(){
			
			//-- retrieve dates from forms --//
			ue_type = $("#ue_type").val();
			startDate = $("#startDate2").val();
			endDate = $("#endDate2").val();
			
			$.ajax({
				
				type:"GET",
				url:"rest/base/date/ue_type",
				data: { ue_type: ue_type, start: startDate, end: endDate },
			    cache: false,
				dataType:"json",
				
				success: function(data) {
					
	
					$("#responseHolder").empty();
					
					$("#responseHolder").append("<li> Count: "+data+"</li>");
				}
	
			});
			
		});	

//--- SEARCH BY DATES FOR NUM FAILURES AND DURATION---//
	
	$("#searchByDatesButton2").click(function(){
		
		//-- retrieve dates from forms --//
		startDate = $("#startDate2").val();
		endDate = $("#endDate2").val();
		
		window.alert("hi");
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/numfail",
			data: { start2: startDate, end2: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {

				$("#responseHolder").empty();
				
				$.each(data, function(index, value){
					$("#responseHolder").append
						("<li> IMSI: "+value+"</li>");
				});	
			}

		});
		
	});	
	
});	