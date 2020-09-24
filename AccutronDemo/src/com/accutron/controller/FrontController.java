package com.accutron.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.accutron.GeneralDto.GeneralDto;
import com.accutron.model.department;
import com.accutron.model.employee;
import com.accutron.service.impl.ServiceImpl;


@Controller
@RequestMapping("/Front")
public class FrontController {
	
	@Autowired
	ServiceImpl service;
	
	
	
	
	public ServiceImpl getService() {
		return service;
	}




	public void setService(ServiceImpl service) {
		this.service = service;
	}




	@RequestMapping(value = "/login")
	public ModelAndView loadstudentData(HttpServletRequest request,HttpServletResponse response)
	{
				ModelAndView model = new ModelAndView("loadstudentData");
				
				String user_name=request.getParameter("username");
				String pass=request.getParameter("password");
				
				boolean flag=service.login(user_name, pass);
				if(flag==true)
				{
					List<department> list=new ArrayList<department>();
					list=service.getDepartment();
					
					List<employee> list1=new ArrayList<employee>();
					list1=service.getEmployee();
					for(int i=0;i<list1.size();i++)
					{
						Calendar c = Calendar.getInstance();
						  c.setTime(list1.get(i).getDob());
						  int year = c.get(Calendar.YEAR);
						  int month = c.get(Calendar.MONTH) + 1;
						  int date = c.get(Calendar.DATE);
						  LocalDate l1 = LocalDate.of(year, month, date);
						  LocalDate now1 = LocalDate.now();
						  Period diff1 = Period.between(l1, now1);
						list1.get(i).setDobtemp(diff1.getYears()+"");
					}
					request.setAttribute("dept", list);
					request.setAttribute("emp", list1);
					return new ModelAndView("employee");
				}
				else
				{
					return model.addObject("msg", "Wrong user name and pass");
				}
	}

	@RequestMapping(value = "/saveEmployeeData")
	public ModelAndView saveEmployeeData(@ModelAttribute("generalDto") GeneralDto generalDto,HttpServletRequest request,
			HttpServletResponse response,@RequestParam("file") MultipartFile[] files) throws IOException
	{
		employee emp=generalDto.getEmp();
		
//		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(emp.getDobtemp());  
//		emp.setDob(date1);
//		
		byte[] fileByteData;boolean fileWriteFlag=true;
		String fileName="",filePath="";
		String emp_id=request.getParameter("employee_id");
		
		if(emp_id!=null && !("").equals(emp_id))
		{
			emp.setEmployee_id(Integer.parseInt(emp_id));
		}
		try
		{
		
		if(fileWriteFlag && files[0] != null && files[0].getSize() > 0) {
			fileName=files[0].getOriginalFilename();
			filePath="e:/img";
			fileByteData=files[0].getBytes();
			BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filePath+"/"+fileName));  
	        bout.write(fileByteData);  
	        bout.flush();  
	        bout.close();  
			emp.setImage(fileName);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		boolean flag=service.saveEmpData(emp);
		
		
		if(flag==true)
		{
			List<department> list=new ArrayList<department>();
			list=service.getDepartment();
			List<employee> list1=new ArrayList<employee>();
			list1=service.getEmployee();
			for(int i=0;i<list1.size();i++)
			{
				Calendar c = Calendar.getInstance();
				  c.setTime(list1.get(i).getDob());
				  int year = c.get(Calendar.YEAR);
				  int month = c.get(Calendar.MONTH) + 1;
				  int date = c.get(Calendar.DATE);
				  LocalDate l1 = LocalDate.of(year, month, date);
				  LocalDate now1 = LocalDate.now();
				  Period diff1 = Period.between(l1, now1);
				list1.get(i).setDobtemp(diff1.getYears()+"");
			}
			request.setAttribute("dept", list);
			request.setAttribute("emp", list1);
		}
		return new ModelAndView("employee");
	}
	
	@RequestMapping(value = "/deleteEmployeeData")
	public ModelAndView deleteEmployeeData(HttpServletRequest request,HttpServletResponse response) throws ParseException
	{
		employee emp=new employee();
		String emp_id=request.getParameter("employee_id");
		
		if(emp_id!=null && !("").equals(emp_id))
		{
			emp.setEmployee_id(Integer.parseInt(emp_id));
		}
		boolean flag=service.deleteEmployee(emp_id);
		if(flag==true)
		{
			request.setAttribute("msg", "deleted");
			List<department> list=new ArrayList<department>();
			list=service.getDepartment();
			List<employee> list1=new ArrayList<employee>();
			list1=service.getEmployee();
			for(int i=0;i<list1.size();i++)
			{
				Calendar c = Calendar.getInstance();
				  c.setTime(list1.get(i).getDob());
				  int year = c.get(Calendar.YEAR);
				  int month = c.get(Calendar.MONTH) + 1;
				  int date = c.get(Calendar.DATE);
				  LocalDate l1 = LocalDate.of(year, month, date);
				  LocalDate now1 = LocalDate.now();
				  Period diff1 = Period.between(l1, now1);
				list1.get(i).setDobtemp(diff1.getYears()+"");
			}
			request.setAttribute("dept", list);
			request.setAttribute("emp", list1);
		}
		return new ModelAndView("employee");
	}
	
}
