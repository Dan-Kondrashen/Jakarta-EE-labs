package domain;

import java.sql.Date;

public class Employee {
	
	// Идентификатор сотрудника
	private Long id;

	// Имя
	private String firstName;

	// Фамилия
	private String lastName;
	 
	// Отчество
	private String secondName;
	 
	// Должность
	private String title;
	 
	// Телефон
	private String phone;

	// email
	private String email;
	 
	// Регион
	private String region;
		 
	// Регион
	private Date birchDay;
	
	// Регион
	private String addres;
	 
	 
	public Employee(Long id,String firstName,String lastName,String secondName, String title
			 ,String phone,String email,String region,Date birchDay,String addres) {
		 this.setId(id);
		 this.setFirstName(firstName);
		 this.setSecondName(secondName);
		 this.setLastName(lastName);
		 this.setTitle(title);
		 this.setPhone(phone);
		 this.setEmail(email);
		 this.setRegion(region);
		 this.setBirchDay(birchDay);
		 this.setAddres(addres);
	}
	public Employee() {}
	 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Date getBirchDay() {
		return birchDay;
	}
	public void setBirchDay(Date birchDay) {
		this.birchDay = birchDay;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	@Override
	public String toString(){
		return "Работник:"+ firstName + " " + secondName + " " + lastName + " вышел на работу";
		
	}
	
	 
	 

}

