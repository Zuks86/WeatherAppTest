package za.co.zlab.weather.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import static za.co.zlab.weather.constants.Constants.DATE_FORMAT;

/**
* WeatherTransformDTO is more of a helper class that assists in transforming the response from 
* the 3rd party API into the form expected by the view
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
public class WeatherTransformDTO extends ViewWeather {
	
	private Date date;
	
	public WeatherTransformDTO() {
		date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return super.toString()
				+"\nDate: "+((new SimpleDateFormat(DATE_FORMAT)).format(date));
				
	}
}
