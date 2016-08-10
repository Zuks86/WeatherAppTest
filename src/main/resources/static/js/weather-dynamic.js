$(window).load(onLoad);

var weatherData;

function onLoad() {
	
	var country = localStorage.country;
	var city = localStorage.city;
	
	if(typeof country == 'undefined') {
		country = "ZA";
		localStorage.country = country;
	}
	if(typeof city == 'undefined') {
		city = "Cape Town";
		localStorage.city = city;
	}
	
	fetchWeatherData(country, city);
}

function fetchWeatherData(country, city) {
	showAjax(true);
	
	var url = "/api/weather";
	var data = {
			country_code: country,
			city: city
	};
	
	$.ajax({
		url: url,
		type: "get",
		data: data,
		dataType: "json",
		success: function(response) {
			weatherData = response;
			
			if(weatherData.details.countryFound) {
				display();
			} else {
				alert("City Not Found");
				showAjax(false);
			}
		},
		error: function(xhr) {
			alert("There was a problem fetching the weather data for "+city+","+country);
			showAjax(false);
		}
	});
}

var maxRange, minRange;

function display() {
	maxRange = [];
	minRange = [];
	var avgMax=0, avgMin=0;
	var set = new CustomSet();
	
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
		
		$("#forecast").append(getWeatherDayHtml("Day "+i,day.high,day.low,day.icon));
	}
	
	avgMax = Math.round(avgMax / 5);
	avgMax = avgMax > 9 ? avgMax : "0"+avgMax;
	
	avgMin = Math.round(avgMin / 5);
	avgMin = avgMin > 9 ? avgMin : "0"+avgMin;
	
	$("#avgMaxValue").html(avgMax+"&deg;C");
	$("#avgMinValue").html(avgMin+"&deg;C");
	
	$("#missingTempsValues").html(set.getMissingValuesString());
	
	plotGraph(weatherData.details.location, maxRange, minRange);
	
	showAjax(false);
}