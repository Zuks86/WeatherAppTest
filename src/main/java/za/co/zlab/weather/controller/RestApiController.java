package za.co.zlab.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.zlab.weather.dto.ViewWeatherResponse;
import za.co.zlab.weather.external.RestApis;

/**
* RestApiController implements the endpoints for the weather api proxy
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@RestController
public class RestApiController {
	
	@Value("${weather.api.default.country_code}")
    public String defaultCountryCode; //Cape Town
	
	@Value("${weather.api.default.city}")
    public String defaultCity; //Cape Town
	
	@Value("${weather.api.default.units}")
    public String defaultUnits; //metric
	
	@Autowired
	public RestApis restApis;

	@RequestMapping(value = "/api/weather", method = RequestMethod.GET)
    public ViewWeatherResponse getUsers(@RequestParam(value = "country_code", required = false) String countryCode, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "units", required = false) String units) {
		
		if(countryCode == null) {
    		countryCode = defaultCountryCode;
    	}
		
    	if(city == null) {
    		city = defaultCity;
    	}
    	
    	if(units == null) {
    		units = defaultUnits;
    	}
    	
    	return restApis.getWeather(countryCode, city, units);
    }
}