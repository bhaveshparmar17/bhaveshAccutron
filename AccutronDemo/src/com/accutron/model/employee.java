package com.accutron.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="employee")
public class employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employee_id;
	private String employee_name;
	private Date dob;
	private String salary;
	private String dept_id;
	private String image;
	
	
	@Transient
	private String dobtemp;
	@Transient
	private MultipartFile IMG_PATH;
	
	
	public MultipartFile getIMG_PATH() {
		return IMG_PATH;
	}
	public void setIMG_PATH(MultipartFile iMG_PATH) {
		IMG_PATH = iMG_PATH;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getDobtemp() {
		return dobtemp;
	}
	public void setDobtemp(String dobtemp) {
		this.dobtemp = dobtemp;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	

}
