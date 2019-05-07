package application;

public class MyLocation {

	private int locationNum;
	private String locationName;
	
	
	public MyLocation(int locationNum, String locationName) {
		super();
		this.locationNum = locationNum;
		this.locationName = locationName;
		
	}
	public int getLocationNum() {
		return locationNum;
	}
	public void setLocationNum(int locationNum) {
		this.locationNum = locationNum;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	@Override
	public String toString() {
		return locationName;
	}
	
	
	
}
