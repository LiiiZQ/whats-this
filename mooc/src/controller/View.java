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
import modle.Score;
import modle.Error;
/*
 * 颜聪----------------老师预览试卷
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SqlOperator con=new SqlOperator();
		String cid=request.getParameter("courseId");
		ArrayList<Score> scorearray=new ArrayList();
		ArrayList<Error> errorarray=new ArrayList();
		Statement state=null;
		Statement state2=null;
		try {
			state=con.con.createStatement();
			state2=con.con.createStatement();
			HttpSession session=request.getSession();
			String sql="select * from result where cid='"+cid+"'";
			String sql2="select error,COUNT(error) as num from error where cid='"+cid+"' group by error";
            ResultSet set=state.executeQuery(sql);
            ResultSet set2=state2.executeQuery(sql2);
            while(set.next())
            {
            	Score score=new Score(set.getString("id"),set.getString("name"),set.getString("score"),set.getString("time"));
            	scorearray.add(score);
            }
            while(set2.next())
            {
            	Error error=new Error(set2.getString("error"),set2.getString("num"));
            	errorarray.add(error);
            }
            session.setAttribute("score", scorearray);
            session.setAttribute("error", errorarray);
            set.close();
            set2.close();
			this.getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
