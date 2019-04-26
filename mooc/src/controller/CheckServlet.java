package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import modle.Question;
/*
 * 颜聪--------实现对学生答卷的评判
 */
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String cid=session.getAttribute("cid").toString();
		String id = session.getAttribute("Id").toString();
		String name = session.getAttribute("name").toString();
		DateFormat df =DateFormat.getDateTimeInstance();//设置日期格式
        String time=df.format(new Date());
		ArrayList q=(ArrayList)session.getAttribute("ques");
		ArrayList<String> user=new ArrayList();
		int count=0;
		SqlOperator con=new SqlOperator();
		try {
			Statement state=con.con.createStatement();
			for(int i=0;i<q.size();i++)
			{
				String number=String.valueOf(i+1);
		        Question tm=(Question)q.get(i);
		        if(request.getParameter(number)!=null)
		        {
		        	user.add(request.getParameter(number));
		        }
		        else
		        {
		        	user.add("未完成");
		        }
		        if(tm.right.equals(request.getParameter(number)))
		        		{
		        	count++;
		        		}
		        else
		        {
		        	String sql="insert into error values('"+cid+"','"+number+"')";
		        	state.execute(sql);
		        }
		        
			}
			request.setAttribute("count",count);
			request.setAttribute("user", user);
			String sql="insert into result values('"+cid+"','"+id+"','"+name+"','"+String.valueOf(count)+"','"+time+"')";
			state.execute(sql);
			this.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}