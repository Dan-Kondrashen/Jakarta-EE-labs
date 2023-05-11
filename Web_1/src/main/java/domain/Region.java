package domain;

public class Region {
	// ID Региона
	private Long regionID;
	// Описание региона
	private String regionDesc;
	
	private String regionName;
		
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
	
	public Region() {
		
	}
	public Region(Long id, String name, String desc) {
		this.setRegionID(id);
		this.setRegionName(name);
		this.setRegionDesc(desc);
		
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	@Override
	public String toString() {
	return "Region {" + "Id = " + regionID  + ", regionName = " + regionName + ", regionDesc = " + regionDesc
	+ "}";
	}

}
