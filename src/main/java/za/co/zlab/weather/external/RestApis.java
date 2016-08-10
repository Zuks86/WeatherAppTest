package za.co.zlab.weather.external;

import org.springframework.stereotype.Service;

import za.co.zlab.weather.dto.ViewWeatherResponse;

/**
* RestApis is an interface with methods that access endpoints.
* The implementing classes are expected to contain logic that calls 3rd party apis, 
* manipulate the responses where applicable and return the response to the view.
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@Service
public interface RestApis {
	public ViewWeatherResponse getWeather(String countryCode, String city, String units);
}
