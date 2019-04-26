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
/*
 * 颜聪-------------------创建新试卷
 */
@WebServlet("/shijuan")
public class shijuan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shijuan() {
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
		try {
			state=con.con.createStatement();
			String courseId=request.getParameter("courseId");
            String sql="delete from question where cid='"+courseId+"'";
            state.execute(sql);	
            sql="delete from result where cid='"+courseId+"'";
            state.execute(sql);
            sql="delete from error where cid='"+courseId+"'";
            state.execute(sql);
			this.getServletContext().getRequestDispatcher("/shijuan.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}