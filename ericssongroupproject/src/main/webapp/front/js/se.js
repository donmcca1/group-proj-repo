$(document).ready(function(){

    //******************//
	//*** SE QUERIES ***//
	//******************//
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	$("#button4").click(function(){
		
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
	
	$("#button5").click(function(){
		
		//-- retrieve dates from forms --//
		ue_type = $("#ue5").val();
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
	$("#button6").click(function(){	
		
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
	
});	