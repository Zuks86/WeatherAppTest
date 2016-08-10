package za.co.zlab.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* Main is one of the classes that form part of the external (3rd party) api representation
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
	private float temp;
	private float temp_min;
	private float temp_max;
	
	public Main() {
		
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(float temp_min) {
		this.temp_min = temp_min;
	}

	public float getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(float temp_max) {
		this.temp_max = temp_max;
	}
	
	
}
