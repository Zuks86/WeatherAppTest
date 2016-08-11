$(window).load(onLoad);
$(window).resize(onResize);

var weatherData, countryCodes;

function onLoad() {
	
	countryCodes = localStorage.countryCodes;
	
	if(typeof countryCodes == 'undefined') {
		fetchCountryCodes();
	}else {
		countryCodes = JSON.parse(countryCodes);
		
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
		
		populateCountryCodes();
		
		fetchWeatherData(country, city);
	}
	
	$("#confirmSearch").click(function(){
		var country = $("#countries").val().trim();
		var city = $("#cityInput").val().trim();
		
		if(city.length == 0) {
			alert("You have to specify the city.");
		}else {
			fetchWeatherData(country, city);
		}
	});
	
	$(window).keypress(function(e){
		if(e.keyCode == 13 && $("#changeCitySpace").is(":visible") ) {
			$("#confirmSearch").click();
		}
	});
}

function onResize() {
	$("#cityInput").width($("#countries").width());
}

function fetchCountryCodes() {
	showAjax(true);
	
	var url = "/js/country_codes.json";
	
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		success: function(response) {
			countryCodes = response;
			
			localStorage.countryCodes = JSON.stringify(countryCodes);
			
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
			
			populateCountryCodes();
			
			fetchWeatherData(country, city);
		},
		error: function(xhr) {
			alert("There was a problem fetching country codes");
			showAjax(false);
		}
	});
}

function populateCountryCodes() {
	var $countries = $("#countries");
	
	$countries.empty();
	for(var i=0;i<countryCodes.length;i++) {
		var entry = countryCodes[i];
		var name = entry.name;
		var code = entry.code;
		
		var $option = $("<option />").val(code).text(name);
		$countries.append($option);
	}
	
	$('#countries').val(localStorage.country);
}

function showCitySearch() {
	$('#changeCitySpace').show();
	
	onResize();
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
				localStorage.country = country;
				localStorage.city = city;
				
				$("#cityInput").val("");
				$("#changeCitySpace").hide();
				
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

var maxRange, minRange, set;

function display() {
	maxRange = [];
	minRange = [];
	var avgMax=0, avgMin=0;
	set = new CustomSet();
	
	$("h2").text(weatherData.details.location);
	$("#day1Value").text(("Day 1 = " + formatDate(new Date(weatherData.details.lastModified))).toUpperCase());
	
	$("#forecast").empty();
	
	var n = 0;
	
	for(var i=1;i<=5;i++) {
		var day = weatherData["day"+i];
		
		if(day != null) {
			n++;
			
			maxRange.push(day.high);
			minRange.push(day.low);
			
			avgMax += day.high;
			avgMin += day.low;
			
			set.add(day.high);
			set.add(day.low);
			
			$("#forecast").append(getWeatherDayHtml("Day "+i,day.high,day.low,day.icon));
		}
	}
	
	avgMax = Math.round(avgMax / n);
	avgMax = avgMax > 9 ? avgMax : "0"+avgMax;
	
	avgMin = Math.round(avgMin / n);
	avgMin = avgMin > 9 ? avgMin : "0"+avgMin;
	
	$("#forecast .forecastDay").css("width","calc(100% / "+n+")")
	
	$("#avgMaxValue").html(avgMax+"&deg;C");
	$("#avgMinValue").html(avgMin+"&deg;C");
	
	$("#missingTempsValues").html(set.getMissingValuesString());
	
	plotGraph(weatherData.details.location, maxRange, minRange, set.getMin());
	
	showAjax(false);
}