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
import modle.Question;
import modle.Kejian;
/*
 * 颜聪--------------将老师上传的课件显示到学生端
 */
@WebServlet("/kejian")
public class Down extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Down() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlOperator con=new SqlOperator();
		Statement state=null;
		String courseId=request.getParameter("courseId");
		ArrayList<Kejian> kejianarray=new ArrayList();
		try {
			state=con.con.createStatement();			
            String sql="select * from kejian where cid='"+courseId+"'";	
            ResultSet rs=state.executeQuery(sql);
            while(rs.next())
            {
            	Kejian kejian=new Kejian(rs.getString("cid"),rs.getString("filename"),rs.getString("title"),rs.getString("explain"),rs.getString("time"));
            	kejianarray.add(kejian);
            }
            request.setAttribute("kejian", kejianarray);
			this.getServletContext().getRequestDispatcher("/kejian.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}