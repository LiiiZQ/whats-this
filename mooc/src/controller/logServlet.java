package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.Comment;
import modle.SqlOperator;
import modle.itClass;

/*
 * 张钰焯------------登陆
 */
@WebServlet("/log")
public class logServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//获取表单的用户名
		        ArrayList<itClass> classarray=new ArrayList();
		        ArrayList<Comment> commentarray=new ArrayList();
				String username=request.getParameter("uName");
				username=new String(username.getBytes("iso-8859-1"),"utf-8");
				//获取表单的密码
				String password=request.getParameter("uPwd");
				String code = request.getParameter("code");
				String checked=request.getParameter("checked");
				Statement state=null;
				Statement state2=null;
				// 验证验证码
				String sessionCode = request.getSession().getAttribute("code").toString();
				SqlOperator so=new SqlOperator();
				try {
					state=so.con.createStatement();
					state2=so.con.createStatement();
					String sql2="select * from comment";
					String sql="select * from class_std natural join class where id='"+username+"'";
					ResultSet rs2=state.executeQuery(sql);
					ResultSet rs3=state2.executeQuery(sql2);
					while(rs2.next())
					{
						itClass itclass=new itClass(rs2.getString("cid"),rs2.getString("tname"),rs2.getString("cname"),rs2.getString("des"),rs2.getString("homework"));
						classarray.add(itclass);
					};
				    while(rs3.next())
		            {
		            	Comment comment=new Comment(rs3.getString("username"),rs3.getString("time"),rs3.getString("content"),rs3.getString("videoid"));
		            	commentarray.add(comment);
		            	
		            }
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs=so.getRS();

				String outcome="/index.jsp";

				


				if(code.equalsIgnoreCase(sessionCode)) {
					try {
						if(username.equals("")||password.equals("")) {
							so.close();
							PrintWriter out = response.getWriter();
							
							out.print("<script>alert('用户名或者密码不能为空'); window.location='index.jsp' </script>");
							out.flush();
							out.close();
						}else {
							while(rs.next()) {
								if(username.equals(rs.getString("id"))&&password.equals(rs.getString("pwd"))) {
									HttpSession session=request.getSession();
									String type=rs.getString("type");
									session.setAttribute("type", type);
									session.setAttribute("name", rs.getString("username"));
									session.setAttribute("Id", rs.getString("id"));
									session.setAttribute("email", rs.getString("email"));
									session.setAttribute("tel", rs.getString("telephone"));
									session.setAttribute("sex", rs.getString("sex"));
									session.setAttribute("des", rs.getString("description"));
									session.setAttribute("psw", rs.getString("pwd"));
									session.setAttribute("class", classarray);
									session.setAttribute("comment", commentarray);
									if(checked!=null)
									{
										Cookie c1=new Cookie("username",username);
										Cookie c2=new Cookie("password",password);
										Cookie c3=new Cookie("checked",checked);
										c1.setMaxAge(1000);
										c2.setMaxAge(1000);
										c3.setMaxAge(1000);
										response.addCookie(c1);
										response.addCookie(c2);
										response.addCookie(c3);
									}
									else
									{
										Cookie[] c=request.getCookies();
										
										if(c!=null)
										{
											for(int i=0;i<c.length;i++)
											{
												Cookie cookie = new Cookie(c[i].getName(), null);
												cookie.setMaxAge(0);												
												response.addCookie(cookie);
											}
										}
									}
									if(type.equals("student"))
									{
									outcome="/log/success.jsp";
									request.getRequestDispatcher("success.jsp").forward(request, response);
									}
									else
									{
										outcome="/log/success.jsp";
										request.getRequestDispatcher("teacher.jsp").forward(request, response);
									}
									
								}    				
							}if(!outcome.equals("/log/success.jsp"))
							so.close();
							PrintWriter out = response.getWriter();
							
							out.print("<script>alert('username or password error'); window.location='index.jsp' </script>");
							out.flush();
							out.close();
						}
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}

				}else {
					so.close();
					PrintWriter out = response.getWriter();
					
					out.print("<script>alert('checkcode error'); window.location='index.jsp' </script>");
					out.flush();
					out.close();
				}
				so.close();
	}

}
