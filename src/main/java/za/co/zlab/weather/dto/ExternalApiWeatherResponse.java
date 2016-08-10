package za.co.zlab.weather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiWeatherResponse {
	private City city;
	private String cod;
	private String message;
	private int cnt;
	private List<WeatherAtTimeEntry> list;

	public ExternalApiWeatherResponse() {
		
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<WeatherAtTimeEntry> getList() {
		return list;
	}

	public void setList(List<WeatherAtTimeEntry> list) {
		this.list = list;
	}
	
	
}
