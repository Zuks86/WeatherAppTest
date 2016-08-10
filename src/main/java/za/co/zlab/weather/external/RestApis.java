package za.co.zlab.weather.external;

import org.springframework.stereotype.Service;

import za.co.zlab.weather.dto.ViewWeatherResponse;

@Service
public interface RestApis {
	public ViewWeatherResponse getWeather(String countryCode, String city, String units);
}
