package za.co.zlab.weather.dto;

public class ViewWeather {
	
	private String condition;
	private int high;
	private int low;
	private String icon;
	
	public ViewWeather() {
		
	}
	
	public ViewWeather(WeatherTransformDTO dto) {
		this.condition = dto.getCondition();
		this.high = dto.getHigh();
		this.low = dto.getLow();
		this.icon = dto.getIcon();
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Condition: "+condition
				+"\nHigh: "+high
				+"\nLow: "+low
				+"\nIcon: "+icon;
				
	}
}
