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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modle.SqlOperator;
import modle.Student;
import modle.itClass;
import modle.Comment;
/*
 * 颜聪----------------将评论存入数据库并显示到相关界面
 */
@WebServlet("/Comment")
public class CreateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateComment() {
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
		Statement state2=null;
		String content=request.getParameter("content");
		content=new String(content.getBytes("iso-8859-1"),"utf-8");
		String name=request.getParameter("name");
		name=new String(name.getBytes("iso-8859-1"),"utf-8");
		String courseId=request.getParameter("courseId");
		DateFormat df =DateFormat.getDateTimeInstance();//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        ArrayList<Comment> array=new ArrayList();
		
		try {
			state=con.con.createStatement();
			String sql="insert into comment values('"+name+"','"+time+"','"+content+"','"+courseId+"')";
           System.out.println(name);
           System.out.println(courseId);
			
			state.execute(sql);
            state2=con.con.createStatement();
            String sql2="select * from comment";
            ResultSet rs=state2.executeQuery(sql2);
            while(rs.next())
            {
            	Comment comment=new Comment(rs.getString("username"),rs.getString("time"),rs.getString("content"),rs.getString("videoid"));
            	array.add(comment);
            }
            HttpSession session=request.getSession();
			session.setAttribute("comment", array);
			request.getRequestDispatcher("video.jsp").forward(request, response);
 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}