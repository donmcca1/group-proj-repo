var BaseData = function(imsi, eventId, causeCode, failureClass, date){
	this.imsi = imsi;
	this.eventId = eventId;
	this.causeCode = causeCode;
	this.failureClass = failureClass;
	this.date = date;
}

$(document).ready(function(){
	
	//--- SEARCH BY IMSI ---//
	$("#searchByImsiButton").click(function(){
		
		var imsi = $("#imsi").val();
		
		$.ajax({
	
			type:"GET",
			url:"rest/base/",
			dataType:"json",
			
			success: function(data) {
				
				$.each(data.baseDataList, function(index, value){
					alert(index + ": " + value.imsi );
					$("#responseHolder").append("<li>"+value.imsi+"</li>");
				});	
			}

		});
		
	});	
	
});	