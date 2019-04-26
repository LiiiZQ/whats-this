package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.SqlOperator;
import modle.Student;
/*
 * 杨海洋-------------------注册
 */
@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName=request.getParameter("methodName");
		int method=Integer.parseInt(methodName);
		try {
			switch(method) {
			case 0:
				insert(request,response);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String returnCard() throws SQLException {
		String cardNum=getCard();
		SqlOperator conn=new SqlOperator();
		Statement state=conn.con.createStatement();
		if(state!=null)System.out.println("2222");
		ResultSet rs=state.executeQuery("select * from user where id="+cardNum); 
		
		if(rs.next()) {
			return returnCard();
		}
		else {
			return cardNum;
		}
	}
	public static String getCard() {
		String str="";
		Random random=new Random();
		
		for(int i=0;i<8;i++){
		    str+=random.nextInt(10);
		}
		return str;
	}
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		String str=returnCard();
		
		int num=Integer.parseInt(str.toString());
		SqlOperator con=new SqlOperator();
		Statement state=null;
		String name=request.getParameter("name");
	    String password=request.getParameter("password");
	    password=new String(password.getBytes("iso-8859-1"),"utf-8");
	    String type=request.getParameter("type");
	    type=new String(type.getBytes("iso-8859-1"),"utf-8");
		
		state=con.con.createStatement();
		state.execute("insert into user(username,id,pwd,type) values('"+name+"',"+num+",'"+password+"','"+type+"')"); 
		state.execute("insert into info values('"+str+"','secret','secret','male','secret')"); 
		con.close();
		PrintWriter out = response.getWriter();
		System.out.println("<script>alert('注册成功，你的账号是:'"+str+"); window.location='index.jsp' </script>");
		out.print("<script>alert('your account:"+str+"'"+"); window.location='index.jsp' </script>");
		out.flush();
		out.close();
	}
}
