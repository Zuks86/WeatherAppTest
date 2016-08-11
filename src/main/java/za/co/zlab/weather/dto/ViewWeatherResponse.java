package za.co.zlab.weather.dto;

import static za.co.zlab.weather.constants.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
* ViewWeatherResponse is the encapsulating DTO class that sends the response to the view 
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
public class ViewWeatherResponse {

	private Details details;
	private ViewWeather day1, day2, day3, day4, day5;
	
	public ViewWeatherResponse(ExternalApiWeatherResponse apiResponse) {
		transform(apiResponse);
	}
	
	public ViewWeather getDay1() {
		return day1;
	}

	public void setDay1(ViewWeather day1) {
		this.day1 = day1;
	}

	public ViewWeather getDay2() {
		return day2;
	}

	public void setDay2(ViewWeather day2) {
		this.day2 = day2;
	}

	public ViewWeather getDay3() {
		return day3;
	}

	public void setDay3(ViewWeather day3) {
		this.day3 = day3;
	}

	public ViewWeather getDay4() {
		return day4;
	}

	public void setDay4(ViewWeather day4) {
		this.day4 = day4;
	}

	public ViewWeather getDay5() {
		return day5;
	}

	public void setDay5(ViewWeather day5) {
		this.day5 = day5;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public void transform(ExternalApiWeatherResponse apiResponse) {
		
		if(apiResponse.getCod().equalsIgnoreCase("200")) {
			
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			
			try {
				Map<String, WeatherTransformDTO> map = new HashMap<String,WeatherTransformDTO>();
				
				Date firstDate = null;
				
				/*
				Will use one weather forecast entry per day. 
				The lowest min temperature for that day is taken and the highest max temperature for that day is taken.
				*/
				for(WeatherAtTimeEntry entry : apiResponse.getList()) {
					Weather weather = entry.getWeather().get(0);
					
					String key = entry.getDt_txt().substring(0, 10);
					
					WeatherTransformDTO dto = new WeatherTransformDTO();
					dto.setCondition(weather.getMain());
					dto.setHigh(Math.round(entry.getMain().getTemp_max()));
					dto.setLow(Math.round(entry.getMain().getTemp_min()));
					dto.setIcon(weather.getIcon());
					dto.setDate(sdf.parse(entry.getDt_txt()));
					
					Date thisDate = sdf.parse(entry.getDt_txt());
					
					if(firstDate == null) {
						firstDate = thisDate;
					} else if(firstDate.after(thisDate)){
						firstDate = thisDate;
					}
					
					if(map.containsKey(key)) {
						WeatherTransformDTO currentForDay = map.get(key);
						
						Calendar cal = Calendar.getInstance();
						
						cal.setTime(dto.getDate());
						int newHour = cal.get(Calendar.HOUR_OF_DAY);
						
						cal.setTime(currentForDay.getDate());
						int currentHour = cal.get(Calendar.HOUR_OF_DAY);
						
						if( Math.abs(12-newHour) < Math.abs(12-currentHour) ) { 
							currentForDay.setCondition(dto.getCondition());
							currentForDay.setIcon(dto.getIcon());
						} else if( Math.abs(12-newHour) == Math.abs(12-currentHour) && newHour > currentHour) {
							currentForDay.setCondition(dto.getCondition());
							currentForDay.setIcon(dto.getIcon());
						}
						
						if(currentForDay.getLow() > dto.getLow()) {
							currentForDay.setLow(dto.getLow());
						}
						if(currentForDay.getHigh() < dto.getHigh()) {
							currentForDay.setHigh(dto.getHigh());
						}
					}else {
						map.put(key, dto);
					}
				}
				
				int i = 1;
				for (String key : (new TreeMap<String, WeatherTransformDTO>(map)).keySet()) {
					
					switch(i) {
				    	case 1:
				    		day1 = new ViewWeather(map.get(key));
				    		break;
				    	case 2:
				    		day2 = new ViewWeather(map.get(key));
				    		break;
				    	case 3:
				    		day3 = new ViewWeather(map.get(key));
				    		break;
				    	case 4:
				    		day4 = new ViewWeather(map.get(key));
				    		break;
				    	case 5:
				    		day5 = new ViewWeather(map.get(key));
				    		break;
			    	}
			    	i++;
			    	
			    	if(i > 5) {
			    		break;
			    	}
				}
				
				details = new Details(firstDate,apiResponse.getCity().getName());
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			details = new Details();
		}
	}
	
	@Override
	public String toString() {
		return "\n"+details+"\n"+
				day1+"\n"+
				day2+"\n"+
				day3+"\n"+
				day4+"\n"+
				day5+"\n\n";
	}
	
}
