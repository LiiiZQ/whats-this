<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="modle.itClass" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <br>
    <h2>课程信息</h2>  <hr>    
    <br> 
    <h3>将课程信息更改为：</h3>
 <form action="DisplayServlet" method="post" >
 <input type="hidden" name="methodName" value="3"/>
<h4>  授课老师：<input type="text" name="tName"></input><br></h4>
<h4>  课程名：<input type="text" name="cName"></input><br></h4>
<h4>  课程简介：<input type="text" name="cDetail"></input><br></h4>
<h4>  课程作业：<input type="text" name="hwork"></input><br></h4>
 <input type="submit" value="修改"/>
  </form>

 <br>
<h3><a href=teacher.jsp>返回</a></h3>
  </body>
</html>