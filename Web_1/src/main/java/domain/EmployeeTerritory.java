package domain;

public class EmployeeTerritory {
	//внешние ключи таблицы для связи работников с территорией
	private long idEmp;
	private long idTer;
	
	public EmployeeTerritory() {
		
	}
	public EmployeeTerritory(Long idEmploc, Long idTerloc) {
		
		this.setIdEmp(idEmploc);
		this.setIdTer(idTerloc);
	}
	public long getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(long idEmp) {
		this.idEmp = idEmp;
	}
	public long getIdTer() {
		return idTer;
	}
	public void setIdTer(long idTer) {
		this.idTer = idTer;
	}
	

}
