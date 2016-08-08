package za.co.zlab.weather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiWeatherResponse {
	private City city;
	private List<WeatherAtTimeEntry> list;

	public ExternalApiWeatherResponse() {
		
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	public List<WeatherAtTimeEntry> getList() {
		return list;
	}

	public void setList(List<WeatherAtTimeEntry> list) {
		this.list = list;
	}
	
	
}
