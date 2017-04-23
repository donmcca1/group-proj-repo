$(document).ready(function(){
	
	//*******************//
	//*** CSR QUERIES ***//
	//*******************//
	
	//waiting icon
	var waiting = '<i class="fa fa-spinner fa-pulse fa-3x fa-fw" style="color:cornflowerblue"></i>';
	
	//--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
	// currently returns all; selection made at front end
	$("#button1").click(function(){	
		
		//-- retrieve data from form --//
		imsi = $("#imsi1").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);

		$.ajax({
			
			type:"GET",
			url:"rest/base/"+imsi,
			dataType:"json",
			
			success: function(data) {
				
				var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>Event ID</th><th>Cause Code</th><th>Description</th></tr></thead>'
					+'<tfoot><tr><th>Event ID</th><th>Cause Code</th><th>Description</th></tr></tfoot>'
					+'<tbody>';
				
				$.each(data.baseDataList, function(index, value){
					
					var newLine = '<tr><td>'+value.event_cause.event_id+'</td><td>'+value.event_cause.cause_code+'</td><td>'+value.event_cause.description+'</td></tr>';
					responseTable+=newLine;
					
				});
				
				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
				
			}
	
		});
	
	});

	//--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
	
	$("#button2").click(function(){
		
		//-- retrieve data from forms --//
		imsi = $("#imsi2").val();
		startDate = $("#startDate2").val();
		endDate = $("#endDate2").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date/imsi",
			data: { imsi: imsi, start: startDate, end: endDate },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
				
				responseTable='<p><b>Count: </b>'+data+'</p>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
				
			}

		});
		
	});

	//--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
    $("#button3").click(function(){
		
		//-- retrieve data from forms --//
        imsi = $("#imsi3").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
        
        $.ajax({

            type:"GET",
            url:"rest/base/cause",
            data: { imsi: imsi },
            dataType:"json",

            success: function(data) {
				
				var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>Cause Code</th><th>Description</th></tr></thead>'
					+'<tfoot><tr><th>Cause Code</th><th>Description</th></tr></tfoot>'
					+'<tbody>';
				
				$("#responseHolder").empty();
				
				var dataArray = data;
				
				$.each(dataArray, function(){
					var str = this.toString();
					var strArray = str.split(",");
					var cause_code = strArray[0];
					var description = strArray[1];
					
					var newLine = '<tr><td>'+cause_code+'</td><td>'+description+'</td></tr>';
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