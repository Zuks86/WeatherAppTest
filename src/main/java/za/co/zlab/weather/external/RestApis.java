package za.co.zlab.weather.external;

import org.springframework.stereotype.Service;

import za.co.zlab.weather.dto.ExternalApiWeatherResponse;

@Service
public interface RestApis {
	public ExternalApiWeatherResponse getWeather(Integer id, String units);
}
