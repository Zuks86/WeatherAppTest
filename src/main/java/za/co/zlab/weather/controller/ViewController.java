package za.co.zlab.weather.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* ViewController handles view mappings
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@Controller
public class ViewController implements ErrorController{
	
	private static final String PATH_404 = "/error";
	private static final String TEMPLATE_HOME = "index";
	private static final String TEMPLATE_WEATHER = "weather";
	
	@RequestMapping("/")
    public String home(Model m) {
        return TEMPLATE_HOME;
    }
	
	@RequestMapping("/static")
    public String staticForecast(Model m) {
		router("static", m);
        return TEMPLATE_WEATHER;
    }
	
	@RequestMapping("/dynamic")
    public String dynamicForecast(Model m) {
		router("dynamic",m);
        return TEMPLATE_WEATHER;
    }
	
	@RequestMapping(value = PATH_404)
    public String error() {
        return "redirect:/";
    }

	@Override
	public String getErrorPath() {
		return PATH_404;
	}
	
	private void router(String type, Model m) {
		String typeFirstUpper = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
		m.addAttribute("type",type);
		m.addAttribute("typeFirstUpper",typeFirstUpper);
	}
}
