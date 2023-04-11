package domain;

public class Territory {
		// ID Территории
		private Long terID;
		// Описание региона
		private String terDesc;
		// Внешний ключ к региону
		private Long regionID;
		public Long getTerID() {
			return terID;
		}
		public void setTerID(Long terID) {
			this.terID = terID;
		}
		public String getTerDesc() {
			return terDesc;
		}
		public void setTerDesc(String terDesc) {
			this.terDesc = terDesc;
		}
		public Long getRegionID() {
			return regionID;
		}
		public void setRegionID(Long regionID) {
			this.regionID = regionID;
		}
	

}
