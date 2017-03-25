$(document).ready(function(){
	
	$(document).ready(function(){
	    $("#searchByImsiButton").prop('disabled',true);
	    $("#imsi").keyup(function(){
	        $("#searchByImsiButton").prop('disabled', this.value == "" ? true : false);     
	    })
	});
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
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

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	
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

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
    $("#cause").click(function(){
        imsi = $("#imsi2").val();

        $.ajax({

            type:"GET",
            url:"rest/base/cause",
            data: {imsi: imsi},
            dataType:"json",

            success: function(data) {

                $("#responseHolder").empty();

                $.each(data.baseDataList, function(index, value){
                    $("#responseHolder").append
                    ("<li> Cause Code: "+value.causeCode+" Failure Class: "+value.failureClass+"</li>");
                });
            }

        });

    });


    //******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
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

	//--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
	
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

	//--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
	
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//--- 7. SELECT BY IMSI & DATE, COUNT FAILURES, SUM DURATION ---//
	
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

	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	
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

	//--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
	
	//--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//

	//***********************//
	//*** SA ONLY QUERIES ***//
	//***********************//
	
	//assign users
	
	//--- DATA IMPORT ---//
	
});	