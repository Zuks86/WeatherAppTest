package za.co.zlab.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* Weather is one of the classes that form part of the external (3rd party) api representation
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	private int id;
	private String main;
	private String description;
	private String icon;
	
	public Weather() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
