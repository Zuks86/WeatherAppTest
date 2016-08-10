package za.co.zlab.weather.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* City is one of the classes that form part of the external (3rd party) api representation
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	private int id;
	private String name;
	private String country;
	private Date date;
	
	public City() {
		date = new Date();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
