package domain;

public class EmployeeTerritory {
	//внешние ключи таблицы для связи работников с территорией
	private long id;
	private long idEmp;
	private long idTer;
	private Territory terr;
	private Employee employ;
	public EmployeeTerritory() {
		
	}
	public EmployeeTerritory(Long id, Long idEmploc, Long idTerloc) {
		this.setId(id);
		this.setIdEmp(idEmploc);
		this.setIdTer(idTerloc);
	}
	
	public EmployeeTerritory(Long id, Long idEmploc, Long idTerloc,Employee emp, Territory terr) {
		this.setId(id);
		this.setIdEmp(idEmploc);
		this.setIdTer(idTerloc);
		this.setEmploy(emp);
		this.setTerr(terr);
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerr() {
		
		return terr.getTerDesc();
	}
	public void setTerr(Territory terr) {
		this.terr = terr;
	}
	public String getEmploy() {
		
		String a = "";
		a = employ.getSecondName() + " " + employ.getFirstName() + " " + employ.getLastName();
		return a;
	}
	public void setEmploy(Employee employ) {
		this.employ = employ;
	}
	@Override
	public String toString() {
	return "EmpToTer {" + "Id = " + id  + ", idEmp = " + idEmp + ", regionDesc = " + idTer
	+ "}";
	}

}
