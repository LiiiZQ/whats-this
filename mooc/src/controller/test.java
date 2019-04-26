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
 *颜聪------------学生端查看试题
 */
@WebServlet("/exam")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlOperator con=new SqlOperator();
		HttpSession session=request.getSession();
		Statement state=null;
		String type=session.getAttribute("type").toString();
		try {
			state=con.con.createStatement();
			ArrayList<Question> question=new ArrayList();
			
			String courseId=request.getParameter("courseId");
            String sql="select * from question where cid='"+courseId+"'";

			ResultSet rs=state.executeQuery(sql);
			if(rs.next())
			{
				Question q=new Question(rs.getString("timu"),rs.getString("qa"),rs.getString("qb"),
						rs.getString("qc"),rs.getString("qd"),rs.getString("right"),rs.getString("explain"));
				question.add(q);
			while(rs.next())
			{
				Question qs=new Question(rs.getString("timu"),rs.getString("qa"),rs.getString("qb"),
						rs.getString("qc"),rs.getString("qd"),rs.getString("right"),rs.getString("explain"));
				question.add(qs);
			}
			session.setAttribute("cid", courseId);
			session.setAttribute("ques", question);
			
			if(type.equals("teacher"))
			{
			this.getServletContext().getRequestDispatcher("/teacherview.jsp").forward(request, response);
			}
			else
			{
			this.getServletContext().getRequestDispatcher("/answer.jsp").forward(request, response);
			}
			}
			else
			{
				String n="no";
				request.setAttribute("n", n);
				if(type.equals("teacher"))
				{
				this.getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
				}
				this.getServletContext().getRequestDispatcher("/stCourse.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}