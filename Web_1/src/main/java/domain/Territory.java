package domain;

public class Territory {
		// ID Территории
		private Long terID;
		// Описание региона
		private String terdesc;
		// Внешний ключ к региону
		private Long regionID;
		
		private Region region;
		
		public Long getTerID() {
			return terID;
		}
		public void setTerID(Long terID) {
			this.terID = terID;
		}
		public String getTerDesc() {
			return terdesc;
		}
		public void setTerDesc(String terDesc) {
			this.terdesc = terDesc;
		}
		public Long getRegionID() {
			return regionID;
		}
		public void setRegionID(Long regionID) {
			this.regionID = regionID;
		}
		public String getRegion() {
			return region.getRegionName();
		}
		public void setRegion(Region region) {
			this.region = region;
		}
		
		public Territory() {}
		
		public Territory(Long terId,String terDesc,Long regId) {
			this.regionID = regId;
			this.terdesc = terDesc;
			this.terID = terId;
		}
		
		public Territory(Long terId, String terDesc, Long regId,Region reg) {
			this.region = reg;
			this.regionID = regId;
			this.terdesc = terDesc;
			this.terID = terId;
		}
		
		public Territory(String terDesc, Long regId,Region reg) {
			this.region = reg;
			this.regionID = regId;
			this.terdesc = terDesc;
		}

		public Territory(String terDesc,Region reg) {
			this.region = reg;
			this.terdesc = terDesc;
			
			
		}
		public Territory(String terDesc, Long regId) {
			this.terdesc = terDesc;
			this.regionID = regId;
		}
		@Override
		public String toString() {
		return "Territory {" + "Id = " + terID + ", terDesc = " + terdesc + ", regID = " + regionID
		+ "}";
		}
}
