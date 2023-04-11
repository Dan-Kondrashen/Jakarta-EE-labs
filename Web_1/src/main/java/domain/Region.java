package domain;

public class Region {
	// ID Региона
	private Long regionID;
	// Описание региона
	private String regionDesc;
		
	public String getRegionDesc() {
		return regionDesc;
	}
	
	public void setRegionDesc(String regionDesc) {
		this.regionDesc = regionDesc;
	}
	
	public Long getRegionID() {
		return regionID;
	}
	
	public void setRegionID(Long regionID) {
		this.regionID = regionID;
	}

}
