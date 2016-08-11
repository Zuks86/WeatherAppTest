$(window).load(onLoad);

var weatherData;

function onLoad() {
	showAjax(true);
	
	$.ajax({
		url: "/js/weather.json",
		type: "get",
		dataType: "json",
		success: function(response) {
			weatherData = response;
			display();
		},
		error: function(xhr) {
			console.log(xhr);
		}
	});
	
}

var maxRange, minRange, set;

function display() {
	maxRange = [];
	minRange = [];
	var avgMax=0, avgMin=0;
	set = new CustomSet();
	
	$("h2").text(weatherData.details.location);
	$("#day1Value").text(("Day 1 = " + formatDate(new Date(weatherData.details.lastModified))).toUpperCase());
	
	$("#forecast").empty();
	for(var i=1;i<=5;i++) {
		var day = weatherData["day"+i];
		
		maxRange.push(day.high);
		minRange.push(day.low);
		
		avgMax += day.high;
		avgMin += day.low;
		
		set.add(day.high);
		set.add(day.low);
		
		var icon = "01d"; //default to "Clear" icon
		
		if(day.condition == "Partly Cloudy") {
			icon = "03d"
		}
		
		$("#forecast").append(getWeatherDayHtml("Day "+i,day.high,day.low,icon));
	}
	
	avgMax = Math.round(avgMax / 5);
	avgMax = avgMax > 9 ? avgMax : "0"+avgMax;
	
	avgMin = Math.round(avgMin / 5);
	avgMin = avgMin > 9 ? avgMin : "0"+avgMin;
	
	$("#avgMaxValue").html(avgMax+"&deg;C");
	$("#avgMinValue").html(avgMin+"&deg;C");
	
	$("#missingTempsValues").html(set.getMissingValuesString());
	
	plotGraph(weatherData.details.location, maxRange, minRange, set.getMin());
	
	showAjax(false);
}