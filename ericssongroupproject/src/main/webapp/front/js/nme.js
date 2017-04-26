$(document).ready(function(){
	
	//*******************//
	//*** NME QUERIES ***//
	//*******************//
	
	//waiting icon
	var waiting = '<i class="fa fa-spinner fa-pulse fa-3x fa-fw" style="color:cornflowerblue"></i>';
	
	var myBarChart = '<canvas id="myBarChart" width="100" height="50"></canvas>'
	
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
					+'<thead><tr><th>IMSI</th><th>Country</th><th>Operator</th><th>Count</th><th>Duration</th></tr></thead>'
					+'<tfoot><tr><th>IMSI</th><th>Country</th><th>Operator</th><th>Count</th><th>Duration</th></tr></tfoot>'
					+'<tbody>';
					
	
				$.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--// 
					var str = value.toString();
					var strArray = str.split(",");
					var imsi = strArray[0];
					var country = strArray[1];
					var operator = strArray[2];
					var count = strArray[3];
					var sum = strArray[4];
						
					var newLine = '<tr><td>'+imsi+'</td><td>'+country+'</td><td>'+operator+'</td><td>'+count+'</td><td>'+sum+'</td></tr>';
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
		
		//-- clears the charts holder, displays waiting icon --//
		$("#charts").empty();
		$("#charts").append(waiting);
		
		//two requests to calculate percents
		$.when(
        
        $.ajax({
            
            type:"GET",
            url:"rest/base/top10MOC",
            data: { start: startDate, end: endDate },
            cache: false,
            dataType:"json",
            
            success: function(data) {

                var responseTable = '<div class="table-responsive">'+'<table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">'
					+'<thead><tr><th>Country</th><th>MCC</th><th>Operator</th><th>MNC</th><th>Cell ID</th><th>Count</th></tr></thead>'
					+'<tfoot><tr><th>Country</th><th>MCC</th><th>Operator</th><th>MNC</th><th>Cell ID</th><th>Count</th></tr></tfoot>'
					+'<tbody>';
					
				var myData = [];
				var myLabels = [];
                
                $.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--//
					var str = value.toString();
					var strArray = str.split(",");
					var country = strArray[0];
					var operator = strArray[1];
					var cell_id = strArray[2];
					var mcc = strArray[3];
					var mnc = strArray[4];
					var count = strArray[5];
                	
					var newLine = '<tr><td>'+country+'</td><td>'+mcc+'</td><td>'+operator+'</td><td>'+mnc+'</td><td>'+cell_id+'</td><td>'+count+'</td></tr>';
					responseTable+=newLine;
					
					myData.push(count);
					var label = mcc+"/"+mnc+"/"+cell_id;
					myLabels.push(label);
                }); 
				
				responseTable+='</tbody></table></div>';
				
				$("#response").empty();
				$("#response").append(responseTable);
				$('#dataTable').dataTable();
				
				$("#charts").empty();
				$("#charts").append(myBarChart);
				
				//add the chart
				var ctx = document.getElementById("myBarChart");
				var myLineChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: myLabels,
						datasets: [{
							label: "Count",
							backgroundColor: "rgba(2,117,216,1)",
							borderColor: "rgba(2,117,216,1)",
							data: myData,
						}],
					},
					options: {
						scales: {
							xAxes: [{
								scaleLabel:{
									display: true,
									labelString: 'MCC/MNC/Cell ID'
								},
								gridLines: {
									display: false
								},
								ticks: {
									maxTicksLimit: 10
								}
							}],
							yAxes: [{
								ticks: {
									min: 0,
									maxTicksLimit: 5
								},
								gridLines: {
									display: true
								}
							}],
						},
						legend: {
							display: false
						}
					}
				});
			}

        }),
		
		$.ajax({ //Second Request(total failures)
                type:"GET",
                url:"rest/base/count",
                cache: false,
                success: function(returnhtml){
					
					var numFailures = returnhtml;
                    //alert(returnhtml);
                }
            })

        ).then(function() {
            
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
					+'<thead><tr><th>IMSI</th><th>Country</th><th>Operator</th><th>Count</th></tr></thead>'
					+'<tfoot><tr><th>IMSI</th><th>Country</th><th>Operator</th><th>Count</th></tr></tfoot>'
					+'<tbody>';
                
                $.each(data, function(index, value){
					
					//--Get results from value string, splitting on commas--//
					var str = value.toString();
					var strArray = str.split(",");
					var imsi = strArray[0];
					var country = strArray[1];
					var operator = strArray[2];
					var count = strArray[3];
					
					var newLine = '<tr><td>'+imsi+'</td><td>'+country+'</td><td>'+operator+'</td><td>'+count+'</td></tr>';
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