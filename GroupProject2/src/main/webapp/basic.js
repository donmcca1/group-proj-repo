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
	$("#searchButton1").click(function(){	
		
		//-- retrieves IMSI from form --//
		imsi = $("#imsi1").val();

		$.ajax({
			
			type:"GET",
			url:"rest/base/"+imsi,
			dataType:"json",
			
			success: function(data) {
	
				$("#responseHolder").empty();
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append
						("<li> Event Id: "+value.event_id+" Cause Code: "+value.cause_code+"</li>");
				});	
			}
	
		});
	
	});

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	
	$("#searchButton2").click(function(){
		
		//-- retrieve dates from forms --//
		imsi = $("#imsi2").val();
		startDate = $("#startDate2").val();
		endDate = $("#endDate2").val();
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date/imsi",
			data: { imsi: imsi, start: startDate, end: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {

				$("#responseHolder").empty();
				
				$("#responseHolder").append("<li> Failure: "+data+"</li>");
			}

		});
		
	});

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
    $("#searchButton3").click(function(){
        imsi = $("#imsi3").val();
        
        $.ajax({

            type:"GET",
            url:"rest/base/cause",
            data: { imsi: imsi },
            dataType:"json",

            success: function(data) {
				
				$("#responseHolder").empty();
				
					$("#responseHolder").append("<li> Cause Codes: "+data+"</li>");
				
			}

        });

    });


    //******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	$("#searchButton4").click(function(){
		
		//-- retrieve dates from forms --//
		startDate = $("#startDate4").val();
		endDate = $("#endDate4").val();
		
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
	
	$("#searchButton5").click(function(){
		
		//-- retrieve dates from forms --//
		ue_type = $("#ue_type5").val();
		startDate = $("#startDate5").val();
		endDate = $("#endDate5").val();
		
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
	// currently returns all; selection made at front end
	$("#searchButton6").click(function(){	
		
		//-- retrieves IMSI from form --//
		cause_code = $("#cause_code6").val();

		$.ajax({
			
			type:"GET",
			url:"rest/base/cause/imsi",
			data: { cause_code: cause_code },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
	
				$("#responseHolder").empty();
				
				$.each(data.baseDataList, function(index, value){
					$("#responseHolder").append
						("<li> imsi: "+value.imsi+"</li>");
				});	
			}
	
		});
	
	});
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//--- 7. SELECT BY IMSI & DATE, COUNT FAILURES, SUM DURATION ---//
	
	$("#searchButton7").click(function(){
		
		//-- retrieve dates from forms --//
		startDate = $("#startDate7").val();
		endDate = $("#endDate7").val();
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/numfail",
			data: { start: startDate, end: endDate },
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
	
	$("#searchButton8").click(function(){
		
		//-- retrieve dates from forms --//
		ue_type = $("#ue_type8").val();
		
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
    $("#searchButton9").click(function(){
        
        //-- retrieve dates from forms --//
        startDate = $("#startDate9").val();
        endDate = $("#endDate9").val();
        
        $.ajax({
            
            type:"GET",
            url:"rest/base/top10",
            data: { start: startDate, end: endDate },
            cache: false,
            dataType:"json",
            
            success: function(data) {

                $("#responseHolder").empty();
                
                $.each(data.baseDataList, function(index, value){
                    $("#responseHolder").append
                        ("<li> Market: "+value.market+"</li>")
                        .append
                        ("<li> Operator: "+value.operator+"</li>")
                        .append
                        ("<li> Cell ID: "+value.cell_id+"</li>");
                }); 
            }

        });
        
    });
	
	//--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//

	//***********************//
	//*** SA ONLY QUERIES ***//
	//***********************//
	
	//assign users
	
	//--- DATA IMPORT ---//
	
});	