package domain;

import java.util.Date;

public class Employee {
	
	// Идентификатор сотрудника
	private Long id;

	// Фамилия
	private String lastName;
		
	// Имя
	private String firstName;

	
	 
	// Отчество
	private String secondName;
	 
	// Должность
	private String title;
	 
	// Телефон
	private String phone;
	
	// Адресс
		private String addres;

	// email
	private String email;
	 
		 
	// Дата рождения
	private Date birchday;
	
	
	 
	 
	public Employee(Long id, String secondName,String firstName,String lastName,String title,String phone,String addres,String email,Date birchDay) {
		 this.setId(id);
		 this.setSecondName(secondName);
		 this.setFirstName(firstName);
		 this.setLastName(lastName);
		 this.setTitle(title);
		 this.setPhone(phone);
		 this.setAddres(addres);
		 this.setEmail(email);
		 this.setBirchDay(birchDay);
		 
	}
	public Employee(String secondName,String firstName,String lastName,String title,String phone,String addres,String email,Date date) {
		 
		 this.setSecondName(secondName);
		 this.setFirstName(firstName);
		 this.setLastName(lastName);
		 this.setTitle(title);
		 this.setPhone(phone);
		 this.setAddres(addres);
		 this.setEmail(email);
		 this.setBirchDay(date);
		 
	}
	public Employee() {}
	 
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirchDay() {
		return birchday;
	}
	public void setBirchDay(Date birchDay) {
		this.birchday = birchDay;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	@Override
	public String toString() {
	return "Employee {" + "Id = " + id  + ", firstName = " + firstName + ", secondName = " + secondName + ", lastName = " + lastName
			+ ", title = " + title + ", phone = " + phone + ", email = " + email + ", birchday = " + birchday + ", addres = " + addres + "}";
	}
	
	 
	 

}

