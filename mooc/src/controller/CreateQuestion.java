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
import javax.servlet.http.HttpSession;

import modle.SqlOperator;
import modle.Student;
import modle.itClass;
/*
 * 
     * 颜聪------------------创建试题
     */

@WebServlet("/question")
public class CreateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlOperator con=new SqlOperator();
		Statement state=null;
		try {
			state=con.con.createStatement();
			HttpSession session=request.getSession();
			String courseId=session.getAttribute("cid").toString();
			String qus=request.getParameter("qus1");
			qus=new String(qus.getBytes("iso-8859-1"),"utf-8");
			String an1=request.getParameter("an1");
			an1=new String(an1.getBytes("iso-8859-1"),"utf-8");
			String an2=request.getParameter("an2");
			an2=new String(an2.getBytes("iso-8859-1"),"utf-8");
			String an3=request.getParameter("an3");
			an3=new String(an3.getBytes("iso-8859-1"),"utf-8");
			String an4=request.getParameter("an4");
			an4=new String(an4.getBytes("iso-8859-1"),"utf-8");
			String right=request.getParameter("right");
			right=new String(right.getBytes("iso-8859-1"),"utf-8");
			String explain=request.getParameter("explain");
			explain=new String(explain.getBytes("iso-8859-1"),"utf-8");
			String sql="insert into question values('"+courseId+"','"+qus+"','"+an1+"','"+an2+"','"+an3+"','"+an4+"','"+right+"','"+explain+"')";
			state.execute(sql);
			this.getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}