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
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/numfail",
			data: { start2: startDate, end2: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {

				$("#responseHolder").empty();
				
				$.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--// 
					
					var full = value.toString();
					var pos = full.indexOf(",");
					var imsi = full.substring(0, pos);
					var pos2 = full.indexOf(",", pos+1);
					var count = full.substring(pos+1, pos2);
					var sum = full.substring(pos2+1);
					
					$("#responseHolder").append
						("<li> IMSI: "+ imsi + " Number of failures: " + count + " Duration of failures: " + sum + "</li>");
				});	
			}

		});
		
	});	
	
	//--- SELECT BY IMSI, COUNT FAILURES BY DATE ---//
	$("#searchByDatesButton1").click(function(){
		
		//-- retrieve dates from forms --//
		imsi = $("#imsi1").val();
		startDate = $("#startDate1").val();
		endDate = $("#endDate1").val();
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date/imsi",
			// imsi: imsi = 500, imsi1: imsi1 = 404
			data: { imsi: imsi, start: startDate, end: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
				//console.log(data);

				$("#responseHolder").empty();
				
				$("#responseHolder").append("<li> Failure: "+data+"</li>");
			}

		});
		
	});
	
	//--- LILY SEARCH BY MODEL RETURN EVENTID/CAUSE CODE COUNTS ---//
	$("#searchByUETypeButton").click(function(){
		
		//-- retrieve dates from forms --//
		ue_type = $("#ue_type").val();
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/ue_type/count",
			data: { ue_type: ue_type },
		    cache: false,
			dataType:"json",
			
			success: function(data) {

				$("#responseHolder").empty();
				
				$.each(data, function(index, value){
				//--Get results from value string, splitting on commas--//
				
					var str = value.toString();
					var strArray = str.split(",");
					var count = strArray[0];
					var event_id = strArray[1];
					var cause_code = strArray[2];
					
					$("#responseHolder").append
						("<li>Count: "+count+" Event ID: "+event_id+" Cause Code: "+cause_code+"</li>");
				});	
			}

		});
		
	});
	
});	