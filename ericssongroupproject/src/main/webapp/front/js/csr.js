$(document).ready(function(){
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
	$("#button1").click(function(){	
		
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
						("<li> Event Id: "+value.event_cause.event_id+";  Cause Code: "+value.event_cause.cause_code+";  Description: "+value.event_cause.description+"</li>");
				});	
			}
	
		});
	
	});

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	
	$("#button2").click(function(){
		
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
				
				$("#responseHolder").append("<li> Failures: "+data+"</li>");
			}

		});
		
	});

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
    $("#button3").click(function(){
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
	
});	