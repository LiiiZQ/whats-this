package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.SqlOperator;
import modle.itClass;

/*
 * 杨海洋-------------老师创建课程的显示
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName=request.getParameter("methodName");
		int method=Integer.parseInt(methodName);
		switch(method) {
		case 0:
			select(request,response);
			break;
		case 1:
			delete(request,response);
			break;
		case 2:
			skip(request,response);
			break;
		case 3:
			update(request,response);
			break;
			} 
		}catch (SQLException e) {
				// TODO 自动生成的 catch 块
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
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			SqlOperator so=new SqlOperator();
		
		Statement state=so.con.createStatement();
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("Id");
		ResultSet rs=state.executeQuery("select * from class where tid="+id);
		ArrayList<itClass> list=new ArrayList<itClass>();
		
		while(rs.next()){ 
		
		String teacherName1 = rs.getString("tname");
		String courseName1 = rs.getString("cname");
		String courseId=rs.getString("cid");
		String homework=rs.getString("homework");
		String courseDetail1=rs.getString("des");
		itClass ci = new itClass(courseId,teacherName1,courseName1,courseDetail1,homework); 
		list.add(ci);
		}
		session.setAttribute("list",list);
		//response.setHeader("refresh","0;url=/mooc/list.jsp");
		request.getRequestDispatcher("list.jsp").forward(request, response);
	    so.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        SqlOperator so=new SqlOperator();
		String courseId=request.getParameter("courseId");
		Statement state=so.con.createStatement();
		state.execute("delete from result where cid='"+courseId+"'");
		state.execute("delete from question where cid='"+courseId+"'");
		state.execute("delete from error where cid='"+courseId+"'");
		state.execute("delete from class_std where cid='"+courseId+"'");
		state.execute("delete from class where cid='"+courseId+"'");
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("Id");
	    Statement state2=so.con.createStatement();
	    ResultSet rs2=state2.executeQuery("select * from class where tid="+id);
		ArrayList<itClass> list=new ArrayList<itClass>();
		
		while(rs2.next()){ 
			
			String teacherName1 = rs2.getString("tname"); 
			String courseName1 = rs2.getString("cname");
			courseId=rs2.getString("cid");
			String homework=rs2.getString("homework");
			String courseDetail1=rs2.getString("des");
			itClass ci = new itClass(courseId,teacherName1,courseName1,courseDetail1,homework); 
			list.add(ci);
		}
		session.setAttribute("courseId",courseId);
		session.setAttribute("list",list);
		response.setHeader("refresh","0;url=/mooc/list.jsp");
		
		
	    
	}
	public void skip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseId=request.getParameter("courseId");
		HttpSession session = request.getSession();
		session.setAttribute("courseId",courseId);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		SqlOperator so=new SqlOperator();
    	Statement stat=null;
    	HttpSession session = request.getSession();
    	String courseId=(String)session.getAttribute("courseId");
    	System.out.println(courseId);
        String teacherName=request.getParameter("tName");
      
        String courseName=request.getParameter("cName");
       
        String homework=request.getParameter("hwork");
      
        String courseDetail=request.getParameter("cDetail");
      
		String id=(String)session.getAttribute("Id");
 		stat=so.con.createStatement();
		stat.execute("update class set tname='"+teacherName+"',cname='"+courseName+"',homework='"+homework+"',des='"+courseDetail+"' where cid='"+courseId+"'");    
		System.out.println("update class set tname='"+teacherName+"',cname='"+courseName+"',homework='"+homework+"',des='"+courseDetail+"' where cid='"+courseId+"'");
		Statement state3=so.con.createStatement();
		    ResultSet rs3=state3.executeQuery("select * from class where tid="+id);
			ArrayList<itClass> list=new ArrayList<itClass>();
			
			while(rs3.next()){ 
				
				String teacherName1 = rs3.getString("tname"); 
				String courseName1 = rs3.getString("cname");
				courseId=rs3.getString("cid");
				homework=rs3.getString("homework");
				String courseDetail1=rs3.getString("des");
				itClass ci = new itClass(courseId,teacherName1,courseName1,courseDetail1,homework); 
				list.add(ci);
			}
			session.setAttribute("list",list);
			response.setHeader("refresh","0;url=/mooc/list.jsp");
	} 

}
