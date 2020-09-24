
package com.accutron.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accutron.model.UserRegister;
import com.accutron.model.department;
import com.accutron.model.employee;




@Repository
public class DaoImpl 
{
	@Autowired
	SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	
	public boolean login(String user,String pass)
	{
		List<UserRegister> list=new ArrayList<UserRegister>();
		Session session = null;
		boolean flag=false;
		try{
			 session = this.getFactory().openSession();
			 StringBuilder sb=new StringBuilder();
			 
			 Query query=session.createQuery("from UserRegister where user_name='"+user+"' and password='"+pass+"'");
			 list=query.list();
			 if(list.size()>0)
			 {
				 flag=true;
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return flag;
	}
	
	public List<department> getDepartment()
	{
		List<department> list=new ArrayList<department>();
		Session session = null;
		boolean flag=false;
		try{
			 session = this.getFactory().openSession();
			 StringBuilder sb=new StringBuilder();
			 
			 Query query=session.createQuery("from department");
			 list=query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
	}
	
	public List<employee> getEmployee()
	{
		List<employee> list=new ArrayList<employee>();
		Session session = null;
		boolean flag=false;
		try{
			 session = this.getFactory().openSession();
			 StringBuilder sb=new StringBuilder();
			 
			 Query query=session.createQuery("from employee");
			 list=query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
	}
	
	public boolean saveEmpData(employee emp) {
		boolean flag = false;
		Session session = null;
		Transaction tx = null;
		
		try{
		 session = this.getFactory().openSession();
		 tx = session.beginTransaction();
		
		session.saveOrUpdate(emp);
		tx.commit();
		flag = true;
		
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			session.clear();
			session.close();
		}
		return flag;
	}
	
	public boolean deleteEmployee(String emp_id)
	{
		boolean flag = false;
		Session session = null;
		Transaction tx = null;
		
		try{
		 session = this.getFactory().openSession();
		 tx = session.beginTransaction();
		 
		 Query query = session.createQuery(" delete from employee where employee_id = '"+emp_id+"' ");
			query.executeUpdate();
			tx.commit();
			flag = true;
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			session.clear();
			session.close();
		}
		return flag;
	}
}
