package POJO;

import java.util.List;

public class LocationObjects {
	
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	public int getVariance() {
		return variance;
	}
	public void setVariance(int variance) {
		this.variance = variance;
	}
	private List<String> cities;
	private int variance;
	

}
