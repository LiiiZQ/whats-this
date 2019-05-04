<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page import="com.yue.model.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>add jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
    <form action="mergeUser.do" method="post" id="addForm">
      <table border="1">
	       <tr>
	          <td>id：</td>
	          <td><input type="text" name="id" value="${user.id }"/></td>
	       </tr>
	       <tr>
	          <td>用户名：</td>
	          <td><input type="text" name="username" value="${user.username }"/></td>
	       </tr>
	       <tr>
	          <td>密码：</td>
	          <td><input type="text" name="password" value="${user.password }"/></td>
	       </tr>
	       <tr>
	          <td>邮箱：</td>
	          <td><input type="text" name="email" value="${user.email }"/></td>
	       </tr>
	       <tr>
	          <td>移动电话：</td>
	          <td><input type="text" name="mobile" value="${user.mobile }"/></td>
	       </tr>
	       <tr>
	          <td>固定电话：</td>
	          <td><input type="text" name="phone" value="${user.phone }"/></td>
	       </tr>
	       <tr>
	          <td>地址：</td>
	          <td><input type="text" name="address" value="${user.address }"/></td>
	       </tr>
	  </table>
	  <input type="submit" value="确定"/>&nbsp;
	  <input type="button" value="返回" onclick="javascript:history.go(-1)"/>
	</form>
  </body>
</html>
