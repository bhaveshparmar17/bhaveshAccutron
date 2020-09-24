<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
  <%@page import="java.io.File"%>
  <%@page import="java.io.*"%>
    <%@page import="java.io.IOException"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="javax.imageio.ImageIO"%>
    <%@page import="java.io.ByteArrayOutputStream"%>

    <%@page import="java.math.BigInteger"%>
    <%@page import="javax.xml.bind.DatatypeConverter"%>
    <%@page import="java.awt.image.BufferedImage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<script type="text/javascript">
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  
  
<form:form modelAttribute="generalDto" name="empFrm" enctype="multipart/form-data">
<div align="center">
<!-- <a href="">Add Employee</a> -->
<!-- <a href="../Front/saveEmployeeData.htm">View Employee</a> -->

<table border="3">
	<tr>
		<td>Employee Name</td>
		<td>
		<input type="hidden" name="employee_id" id="employee_id" value=""> 
		<input type="text" name="emp.employee_name" id="employee_name"> </td>
	</tr>
		<tr>
		<td>Date of Birth</td>
		<td>
		<input type="text" name="emp.dob" id="datepicker">
		</td>
	</tr>
	<tr>
		<td>Salary</td>
		<td><input type="text" name="emp.salary" id="salary"> </td>
	</tr>
	<tr>
		<td>Department</td>
		<td>
			<select name="emp.dept_id" id="dept_id">
				<option value="-1">---Select---</option>
				<c:forEach var="lis" items="${dept}">
					<option value="${lis.dept_id}">${lis.dept_name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>Image</td>
		<td>
			<input type="file" name="file">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button"  name="save" id="save" value="Save" onclick="saveEmp();" /></td>
	</tr>
	
</table>

</div>

<table align="center" border="3">
	<tr>
	<th>Employee Name</th>
 	<th>Age</th> 
 	<th>Salary</th> 
 	<th>Department Name</th> 
	<th>Image</th> 
 	<th>Edit</th> 
	<th>Delete</th> 
	</tr>
	<c:forEach var="emplst" items="${emp}">
	<tr>
		<td>
		${emplst.employee_name}
		</td>
		<td>${emplst.dobtemp}</td>
		<td>${emplst.salary}</td>
		<td>
		<c:forEach var="dept" items="${dept}"> 
 		<c:if test="${dept.dept_id eq emplst.dept_id}"> 
 			${dept.dept_name}	
 		</c:if> 
 	</c:forEach> 
		</td>
		<td>
		
		
	<c:set var="imgpath" value="e:\\img\\${emplst.image}" scope="request"/>
<%
  //write image
  try{

	String imgName=request.getAttribute("imgpath").toString();
    
	  BufferedImage bImage = ImageIO.read(new File(imgName));
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write( bImage, "jpg", baos );
      baos.flush();
      byte[] imageInByteArray = baos.toByteArray();
      baos.close();                                  
        //give the path of an image ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write( bImage, "jpg", baos ); baos.flush(); 
     
      baos.close();
      String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
      %>
      <img  class="img-responsive" src="data:image/jpg;base64, <%=b64%>" height="100px" width="100px"/>                            
      <%
  }catch(IOException e){
    
  }


  %>
  
  <%
  %>
		<img scr="e:\\img\Webp.net-resizeimage (2).jpg">
		</td>
		<td>
		  <input type="button" value="Edit" name="editbtn" onclick="EditEmp('${emplst.employee_id}',
		  '${emplst.employee_name }','${emplst.dob}','${emplst.salary}','${emplst.dept_id}');" />
		</td>
		<td><input type="button" name="deletebtn" value="Delete" onclick="DeleteEmp('${emplst.employee_id}');" />
		</td>
	</tr>
	</c:forEach>
</table>



</form:form>
<script type="text/javascript">
function saveEmp()
{
	document.empFrm.action="../Front/saveEmployeeData.htm";
	document.empFrm.submit();
}

function EditEmp(empid,empname,dob,sal,dept)
{
	document.getElementById("employee_id").value=empid;
	document.getElementById("employee_name").value=empname;
	var $datepicker = $('#datepicker');
	$datepicker.datepicker();
	$datepicker.datepicker('setDate', new Date(dob));
	document.getElementById("salary").value=sal;
	document.getElementById("dept_id").value=dept;
	document.getElementById("save").value="Update";
}

function DeleteEmp(empid)
{
	document.getElementById("employee_id").value=empid;
	document.empFrm.action="../Front/deleteEmployeeData.htm";
	document.empFrm.submit();
}
</script>




</body>
</html>