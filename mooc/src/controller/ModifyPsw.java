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
/*
 * 颜聪-----------------修改密码
 */
@WebServlet("/Mpsw")
public class ModifyPsw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPsw() {
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
		String npsw=request.getParameter("npsw");
		String psw=request.getParameter("psw");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		name=new String(name.getBytes("iso-8859-1"),"utf-8");
		String sex=request.getParameter("sex");
		sex=new String(sex.getBytes("iso-8859-1"),"utf-8");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		String des=request.getParameter("des");
		des=new String(des.getBytes("iso-8859-1"),"utf-8");
		try {
			state=con.con.createStatement();
			String sql="update user set pwd='"+npsw+"' where id='"+id+"'";
			
			System.out.println(sql);

			state.execute(sql);
			HttpSession session=request.getSession();
			session.setAttribute("psw",npsw);
			session.setAttribute("Id",id);
			session.setAttribute("name", name);
			
			session.setAttribute("email",email);
			session.setAttribute("tel", tel);
			session.setAttribute("sex", sex);
			session.setAttribute("des", des);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}