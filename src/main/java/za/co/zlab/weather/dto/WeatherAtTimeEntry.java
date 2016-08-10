package za.co.zlab.weather.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* WeatherAtTime is one of the classes that form part of the external (3rd party) api representation
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAtTimeEntry {
	
	private Main main;
	private List<Weather> weather;
	private String dt_txt;
	
	
	public WeatherAtTimeEntry() {
		
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	
	
}
