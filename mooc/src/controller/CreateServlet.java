package controller;

import java.io.IOException;
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
import modle.courseInfo;

/*
 * 杨海洋-------------创建课堂
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @throws SQLException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	public String returnCard() throws SQLException {
		String cardNum=getCard();
		SqlOperator conn=new SqlOperator();
		Statement state=conn.con.createStatement();
		ResultSet rs=state.executeQuery("select * from class where cid="+cardNum); 
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
		
		for(int i=0;i<6;i++){
		    str+=random.nextInt(10);
		}
		return str;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
		String str=returnCard();
		int courseId=Integer.parseInt(str.toString());
		HttpSession session = request.getSession();
		String teacherName=request.getParameter("teacherName");
		teacherName=new String(teacherName.getBytes("iso-8859-1"),"utf-8");
		String id=(String) session.getAttribute("Id");
		String courseName=request.getParameter("courseName");
		courseName=new String(courseName.getBytes("iso-8859-1"),"utf-8");
		String courseDetail=request.getParameter("courseDetail");
		courseDetail=new String(courseDetail.getBytes("iso-8859-1"),"utf-8");
		SqlOperator con=new SqlOperator();
		Statement state=null;

		
			state=con.con.createStatement();
		    state.execute("insert into class values('"+courseId+"',"+id+",'"+teacherName+"','"+courseName+"','"+courseDetail+"',' ')");
			con.close();
			response.setHeader("refresh","1;url=/mooc/teacher.jsp");
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}

}
