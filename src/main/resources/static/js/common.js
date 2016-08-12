$(window).load(onLoadCommon);
$(window).resize(onResizeCommon);

var monthNames = ["January", "February", "March", "April", "May", "June",
                  "July", "August", "September", "October", "November", "December"];

function onLoadCommon() {
	// code to execute on load completion for all pages
}

function onResizeCommon() {
	// code to call when all pages resize
}

function showAjax(show) {
	if(show) {
		$(".ajax").parent().show()
	}else {
		$(".ajax").parent().hide()
	}
}

class CustomSet {
	constructor() {
		this.array = [];
	}
  
	add(value) {
		if(this.array.indexOf(value) == -1){this.array.push(value)}
	}
  
	remove(value) {
		var index = this.array.indexOf(value);
		if (index > -1) {
			this.array.splice(index, 1);
		}
	}
	
	getMin() {
		return Math.min.apply( Math, this.array );
	}
	
	getMax() {
		return Math.max.apply( Math, this.array );
	}
	
	getMissingValues() {
		var array = this.getSet();
		var min = this.getMin();
		var max = this.getMax();
		
		var missingValuesArray = [];
		
		for(var i=min;i<=max;i++) {
			if(array.indexOf(i) == -1) {
				missingValuesArray.push(i);
			}
		}
		
		return missingValuesArray;
	}
	
	getMissingValuesString() {
		var array = this.getMissingValues();
		
		var string = "";
		for(var i=0;i<array.length;i++) {
			
			string += i == 0 ? array[i] : (", "+array[i]);
		}
		
		return "["+string+"]";
	}

	getSet() {
		return this.array.sort(function(a, b){return a-b});
	}
}

function getWeatherDayHtml(name,max,min,icon) {
	
	//icon = icon.replace("n","d")
	
	var maxStr, minStr;
	
	var maxStr = Math.abs(max) > 9 ? (max+"&deg;C") : (max > 0 ? "0"+max+"&deg;C" : "-0"+Math.abs(max)+"&deg;C");
	var minStr = Math.abs(min) > 9 ? (min+"&deg;C") : (min > 0 ? "0"+min+"&deg;C" : "-0"+Math.abs(min)+"&deg;C");
	
	var $maxTemp = $("<span />").addClass("maxTemp").html(maxStr);
	var $minTemp = $("<span />").addClass("minTemp").html(minStr);
	
	var $temp = $("<div />").addClass("temp").addClass("horizontalCenter");
	$temp.append($maxTemp).append($minTemp);
	
	var src = "http://openweathermap.org/img/w/<ICON>.png".replace("<ICON>",icon);
	var $img = $("<img />").addClass("hvCenter").attr("src",src);
	
	var $name = $("<div />").addClass("name").addClass("horizontalCenter").text(name);
	
	var $forecastDay = $("<div />").addClass("forecastDay");
	$forecastDay.append($name);
	$forecastDay.append($img);
	$forecastDay.append($temp);
	
	return $forecastDay;
}

function formatDate(date) {
	var year = (date).getFullYear();
	var month = monthNames[date.getMonth()];
	var day = date.getDate();
	
	return day + " " + month + " " + year;
}

function plotGraph(cityName, maxRange, minRange, minValue) {
	$('#graph').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Temperature for '+cityName
        },
        xAxis: {
            categories: [
                'Day 1',
                'Day 2',
                'Day 3',
                'Day 4',
                'Day 5'
            ]
        },
        yAxis: [{
            min: Math.min(0,parseInt(minValue)),
            title: {
                text: 'Temperature (°C)'
            }
        }],
        legend: {
            shadow: false
        },
        tooltip: {
            shared: true
        },
        plotOptions: {
            column: {
                grouping: false,
                shadow: false,
                borderWidth: 0
            }
        },
        series: [{
            name: 'High',
            color: 'red',
            data: maxRange,
            pointPadding: 0.2,
            pointPlacement: 0
        }, {
            name: 'Low',
            color: 'orange',
            data: minRange,
            pointPadding: 0.3,
            pointPlacement: 0
        }]
    });
}