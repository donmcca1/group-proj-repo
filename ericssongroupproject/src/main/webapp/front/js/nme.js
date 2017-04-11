$(document).ready(function(){
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//--- 7. SELECT BY IMSI & DATE, COUNT FAILURES, SUM DURATION ---//
	
	$("#button7").click(function(){
		
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
						("<li> IMSI: "+ imsi + "; Number of failures: " + count + "; Duration of failures: " + sum + "</li>");
				});	
			}

		});
		
	});	

	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	
	$("#button8").click(function(){
		
		//-- retrieve dates from forms --//
		ue_type = $("#ue8").val();
		
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
					var description = strArray[3];
					
					$("#responseHolder").append
						("<li>Count: "+count+"; Event ID: "+event_id+"; Cause Code: "+cause_code+"; Description: " + description +"</li>");
				});	
			}

		});
		
	});

    //--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
    $("#button9").click(function(){
        
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
    $("#button10").click(function(){
        
        //-- retrieve dates from forms --//
        startDate = $("#startDate10").val();
        endDate = $("#endDate10").val();
        
        $.ajax({
            
            type:"GET",
            url:"rest/base/top10imsi",
            data: { start: startDate, end: endDate },
            cache: false,
            dataType:"json",
            
            success: function(data) {

                $("#responseHolder").empty();
                
                $.each(data.baseDataList, function(index, value){
                    $("#responseHolder").append
                        ("<li>"+value.imsi+"</li>");
                }); 
            }

        });
        
    });
	
});	