package za.co.zlab.weather.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class RestApiController {

	@Value("${weather.api.key}")
    private String key;

	@RequestMapping(value = "/api/key", method = RequestMethod.GET)
    public String getUsers() {
		return key;
    }
	
    /*@RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public UsersFetchAllDTO getUsers(@RequestParam("id") Integer id, @RequestParam("token") String token) {
		User user = new User();
		user.setId(id);
		user.setToken(token);
        return userRepository.findAll(user);
    }*/
}