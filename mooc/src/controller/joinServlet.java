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
 * 颜聪-----------------学生端加入课堂
 */
@WebServlet("/join")
public class joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public joinServlet() {
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
		Statement state2=null;
		Statement state3=null;
		String cid=request.getParameter("courseId");
		String id=session.getAttribute("Id").toString();
		ArrayList<itClass> array=new ArrayList();
		
		try {
			state=con.con.createStatement();
			state2=con.con.createStatement();
			state3=con.con.createStatement();
			String sql="select * from class_std where id='"+id+"'and cid='"+cid+"'";
            ResultSet rs=state.executeQuery(sql);
            while(rs.next())
            {
            	if(rs.getString("cid").equals(cid)&&rs.getString("id").equals(id))
            	{
            		String join="fail";
            		request.setAttribute("join", join);            		
            		this.getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
            	System.out.println(join);
            	}
            }
            sql="insert into class_std values('"+cid+"','"+id+"')";    
            String sql3="select * from class where cid='"+cid+"'";
            state2.execute(sql);
            ResultSet rs3=state3.executeQuery(sql3);
            ArrayList classarray=(ArrayList)session.getAttribute("class");
            while(rs3.next())
            {
            	itClass itclass=new itClass(rs3.getString("cid"),rs3.getString("tname"),rs3.getString("cname"),rs3.getString("des"),rs3.getString("homework"));
				classarray.add(itclass);
            }
            String join="success";
            System.out.println(join);
            session.setAttribute("class", classarray);
            request.setAttribute("join", join);
            this.getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}