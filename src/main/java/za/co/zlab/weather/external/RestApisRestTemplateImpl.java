package za.co.zlab.weather.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import za.co.zlab.weather.dto.ExternalApiWeatherResponse;

@Service
public class RestApisRestTemplateImpl implements RestApis {
	
	@Value("${weather.api.key}")
    private String key;

	public ExternalApiWeatherResponse getWeather(Integer id, String units) {
		
		String url = "http://api.openweathermap.org/data/2.5/forecast/city?units=<UNITS>&id=<CITY_ID>&APPID=<APP_ID>";
		url = url.replace("<CITY_ID>", id.toString());
		url = url.replace("<UNITS>", units);
		url = url.replace("<APP_ID>", key);
		
		RestTemplate restTemplate = new RestTemplate();
		ExternalApiWeatherResponse response = restTemplate.getForObject(url, ExternalApiWeatherResponse.class);
		
		return response;
	}

}
