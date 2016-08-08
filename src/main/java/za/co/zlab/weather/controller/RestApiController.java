package za.co.zlab.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.zlab.weather.dto.ExternalApiWeatherResponse;
import za.co.zlab.weather.external.RestApis;

@RestController
public class RestApiController {
	
	@Value("${weather.api.default.city}")
    public Integer defaultCityId; //Cape Town City Id
	
	@Value("${weather.api.default.units}")
    public String defaultUnits; //metric
	
	@Autowired
	public RestApis restApis;

	@RequestMapping(value = "/api/weather", method = RequestMethod.GET)
    public ExternalApiWeatherResponse getUsers(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer id, @RequestParam(value = "units", required = false) String units) {
		
    	if(id == -1) {
    		id = defaultCityId;
    	}
    	
    	if(units == null) {
    		units = defaultUnits;
    	}
    	
    	return restApis.getWeather(id, units);
    }
}