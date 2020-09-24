package com.accutron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accutron.dao.impl.DaoImpl;
import com.accutron.model.UserRegister;
import com.accutron.model.department;
import com.accutron.model.employee;


@Service
public class ServiceImpl  
{
	@Autowired 
	DaoImpl Dao;

	public DaoImpl getDao() {
		return Dao;
	}

	public void setDao(DaoImpl dao) {
		Dao = dao;
	}
	
	public boolean saveEmpData(employee emp) {
		
		return Dao.saveEmpData(emp);
	}
	
	public boolean login(String user,String pass)
	{
		return Dao.login(user, pass);
	}
	
	public List<department> getDepartment()
	{
		return Dao.getDepartment();
	}	
	
	public List<employee> getEmployee()
	{
		return Dao.getEmployee();
	}
	public boolean deleteEmployee(String emp_id)
	{
		return Dao.deleteEmployee(emp_id);
	}
	
}
