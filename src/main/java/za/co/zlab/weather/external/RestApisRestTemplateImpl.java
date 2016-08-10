package za.co.zlab.weather.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import za.co.zlab.weather.dto.ExternalApiWeatherResponse;
import za.co.zlab.weather.dto.ViewWeatherResponse;

@Service
public class RestApisRestTemplateImpl implements RestApis {
	
	@Value("${weather.api.key}")
    private String key;

	public ViewWeatherResponse getWeather(String countryCode, String city, String units) {
		
		String url = "http://api.openweathermap.org/data/2.5/forecast?units=<UNITS>&q=<CITY>&appid=<APP_ID>";
		url = url.replace("<UNITS>", units);
		url = url.replace("<CITY>", city+","+countryCode);
		url = url.replace("<APP_ID>", key);
		
		System.out.println(url);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ExternalApiWeatherResponse response = restTemplate.getForObject(url, ExternalApiWeatherResponse.class);
		
		ViewWeatherResponse vwr = new ViewWeatherResponse(response);
		
		System.out.println(vwr);
		
		return vwr;
	}

}
