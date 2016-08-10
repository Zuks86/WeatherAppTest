package za.co.zlab.weather.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import static za.co.zlab.weather.constants.Constants.DATE_FORMAT_DATE_ONLY;

/**
* Details is one of the classes form part of the DTO used to send data to the view
*
* @author  Zukisani Zamela
* @version 1.0
* @since   2016-08-10
*/
public class Details {
	private String lastModified;
	private String location;
	private boolean countryFound;
	
	public Details() {
		
	}
	
	public Details(Date lastModified, String location) {
		this.lastModified = (new SimpleDateFormat(DATE_FORMAT_DATE_ONLY)).format(lastModified);
		this.location = location;
		this.countryFound = true;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isCountryFound() {
		return countryFound;
	}

	public void setCountryFound(boolean countryFound) {
		this.countryFound = countryFound;
	}
	
	@Override
	public String toString() {
		return "Country Found: "+countryFound+
				"\nLast Modified: "+lastModified+
				"\nLocation: "+location;
	}
	
}
