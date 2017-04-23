$(document).ready(function(){
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//waiting icon
	var waiting = '<i class="fa fa-spinner fa-pulse fa-3x fa-fw" style="color:cornflowerblue"></i>';
	
	//--- 7. SELECT BY IMSI & DATE, COUNT FAILURES, SUM DURATION ---//
	
	$("#button7").click(function(){
		
		//-- retrieve data from forms --//
		startDate = $("#startDate7").val();
		endDate = $("#endDate7").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/numfail",
			data: { start: startDate, end: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
				
				var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>IMSI</th><th>Count</th><th>Duration</th></tr></thead>'
					+'<tfoot><tr><th>IMSI</th><th>Count</th><th>Duration</th></tr></tfoot>'
					+'<tbody>';
					
	
				$.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--// 
					var full = value.toString();
					var pos = full.indexOf(",");
					var imsi = full.substring(0, pos);
					var pos2 = full.indexOf(",", pos+1);
					var count = full.substring(pos+1, pos2);
					var sum = full.substring(pos2+1);
						
					var newLine = '<tr><td>'+imsi+'</td><td>'+count+'</td><td>'+sum+'</td></tr>';
					responseTable+=newLine;
				});
				
				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
			}

		});
		
	});	

	//--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
	
	$("#button8").click(function(){
		
		//-- retrieve data from forms --//
		ue_type = $("#ue8").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/ue_type/count",
			data: { ue_type: ue_type },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
				
				var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>Event ID</th><th>Cause Code</th><th>Description</th><th>Count</th></tr></thead>'
					+'<tfoot><tr><th>Event ID</th><th>Cause Code</th><th>Description</th><th>Count</th></tr></tfoot>'
					+'<tbody>';
				
				$.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--//
					var str = value.toString();
					var strArray = str.split(",");
					var count = strArray[0];
					var event_id = strArray[1];
					var cause_code = strArray[2];
					var description = strArray[3];
					
					var newLine = '<tr><td>'+event_id+'</td><td>'+cause_code+'</td><td>'+description+'</td><td>'+count+'</td></tr>';
					responseTable+=newLine;
				});

				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
			}

		});
		
	});

    //--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
    $("#button9").click(function(){
        
        //-- retrieve data from forms --//
        startDate = $("#startDate9").val();
        endDate = $("#endDate9").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
        
        $.ajax({
            
            type:"GET",
            url:"rest/base/top10MOC",
            data: { start: startDate, end: endDate },
            cache: false,
            dataType:"json",
            
            success: function(data) {

                var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>Market</th><th>Operator</th><th>Cell ID</th></tr></thead>'
					+'<tfoot><tr><th>Market</th><th>Operator</th><th>Cell ID</th></tr></tfoot>'
					+'<tbody>';
                
                $.each(data.baseDataList, function(index, value){
                	
					var newLine = '<tr><td>'+value.mcc_mnc.country+'</td><td>'+value.mcc_mnc.operator+'</td><td>'+value.cell_id+'</td></tr>';
					responseTable+=newLine;
					
                }); 
				
				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
            }

        });
        
    });
    
  //--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//
    $("#button10").click(function(){
        
        //-- retrieve data from forms --//
        startDate = $("#startDate10").val();
        endDate = $("#endDate10").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
        
        $.ajax({
            
            type:"GET",
            url:"rest/base/top10imsi",
            data: { start: startDate, end: endDate },
            cache: false,
            dataType:"json",
            
            success: function(data) {

                var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>IMSI</th></tr></thead>'
					+'<tfoot><tr><th>IMSI</th></tr></tfoot>'
					+'<tbody>';
                
                $.each(data.baseDataList, function(index, value){
					
					var newLine = '<tr><td>'+value.imsi+'</td></tr>';
					responseTable+=newLine;
                });

				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
            }

        });
        
    });
	
});	