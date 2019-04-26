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
 *颜聪---------------- 搜索课堂
 */
@WebServlet("/search")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
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
		Statement state3=null;
		HttpSession session=request.getSession();
		String cid=request.getParameter("class");
		cid=new String(cid.getBytes("iso-8859-1"),"utf-8");
		ArrayList<itClass> array=new ArrayList();
		
		try {
			state=con.con.createStatement();
			state2=con.con.createStatement();
			state3=con.con.createStatement();
			String sql="select * from class where cid='"+cid+"'";
			String sql2="select * from class where cname like '%"+cid+"%'";
			String sql3="select * from class where tid='"+cid+"'";
			ResultSet rs=state.executeQuery(sql);
			ResultSet rs2=state2.executeQuery(sql2);
			ResultSet rs3=state3.executeQuery(sql3);
			while(rs.next())
			{
				int n=1;
				itClass itclass=new itClass(rs.getString("cid"),rs.getString("tid"),rs.getString("tname"),rs.getString("cname"),rs.getString("des"),rs.getString("homework"));
				for(int i=0;i<array.size();i++)
				{
					itClass it=array.get(i);
					if(rs.getString("cid").equals(it.id))
					{
						n=2;
					}
				}
				if(n==1)
				{
					array.add(itclass);
				}
				
			}
			while(rs2.next())
			{
				int n=1;
				itClass itclass=new itClass(rs2.getString("cid"),rs2.getString("tid"),rs2.getString("tname"),rs2.getString("cname"),rs2.getString("des"),rs2.getString("homework"));
				for(int i=0;i<array.size();i++)
				{
					itClass it=array.get(i);
					if(rs2.getString("cid").equals(it.id))
					{
						n=2;
					}
				}
				if(n==1)
				{
					array.add(itclass);
				}
			}
			while(rs3.next())
			{
				int n=1;
				itClass itclass=new itClass(rs3.getString("cid"),rs3.getString("tid"),rs3.getString("tname"),rs3.getString("cname"),rs3.getString("des"),rs3.getString("homework"));
				for(int i=0;i<array.size();i++)
				{
					itClass it=array.get(i);
					if(rs3.getString("cid").equals(it.id))
					{
						n=2;
					}
				}
				if(n==1)
				{
					array.add(itclass);
				}
			}

		    session.setAttribute("search", array);
		    this.getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}