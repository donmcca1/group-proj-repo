$(document).ready(function(){

    //******************//
	//*** SE QUERIES ***//
	//******************//
	
	//waiting icon
	var waiting = '<i class="fa fa-spinner fa-pulse fa-3x fa-fw" style="color:cornflowerblue"></i>';
	
	//--- 4. SELECT BY DATE, RETURN IMSI ---//
	// currently returns all; selection made at front end
	$("#button4").click(function(){
		
		//-- retrieve data from forms --//
		startDate = $("#startDate4").val();
		endDate = $("#endDate4").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date",
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

	//--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
	
	$("#button5").click(function(){
		
		//-- retrieve data from forms --//
		ue_type = $("#ue5").val();
		startDate = $("#startDate5").val();
		endDate = $("#endDate5").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);
		
		$.ajax({
			
			type:"GET",
			url:"rest/base/date/ue_type",
			data: { ue_type: ue_type, start: startDate, end: endDate },
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

	//--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
	// currently returns all; selection made at front end
	$("#button6").click(function(){	
		
		//-- retrieve data from forms --//
		cause_code = $("#cause_code6").val();
		
		//-- clears the response holder, displays waiting icon, scrolls to top --//
		$("#response").empty();
		$("#response").append(waiting);
		$(window).scrollTop(0);

		$.ajax({
			
			type:"GET",
			url:"rest/base/cause/imsi",
			data: { cause_code: cause_code },
		    cache: false,
			dataType:"json",
			
			success: function(data) {
				
				var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>IMSI</th></tr></thead>'
					+'<tfoot><tr><th>IMSI</th></tr></tfoot>'
					+'<tbody>';
	
				$("#responseHolder").empty();
				
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