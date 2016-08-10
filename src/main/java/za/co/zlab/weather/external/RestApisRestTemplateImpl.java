package za.co.zlab.weather.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import za.co.zlab.weather.dto.ExternalApiWeatherResponse;
import za.co.zlab.weather.dto.ViewWeatherResponse;

/**
* RestApisRestTemplateImpl implements RestApi using RestTemplate to call the 3rd party api.
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@Service
public class RestApisRestTemplateImpl implements RestApis {
	
	@Value("${weather.api.key}")
    private String key;

	public ViewWeatherResponse getWeather(String countryCode, String city, String units) {
		
		String url = "http://api.openweathermap.org/data/2.5/forecast?units=<UNITS>&q=<CITY>&appid=<APP_ID>";
		url = url.replace("<UNITS>", units);
		url = url.replace("<CITY>", city+","+countryCode);
		url = url.replace("<APP_ID>", key);
		
		RestTemplate restTemplate = new RestTemplate();
		
		return new ViewWeatherResponse(restTemplate.getForObject(url, ExternalApiWeatherResponse.class));
	}

}
