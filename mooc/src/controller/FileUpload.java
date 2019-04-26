package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.FtpTool;
import modle.SqlOperator;

/*
 * 张钰焯、杨海洋--------------------老师上传课件
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getSession().getAttribute("cid").toString();
		String title=request.getParameter("title");
		//title=new String(title.getBytes("iso-8859-1"),"utf-8");
		String explain=request.getParameter("explain");
		//explain=new String(explain.getBytes("iso-8859-1"),"utf-8");
		DateFormat df =DateFormat.getDateTimeInstance();
        String time=df.format(new Date());
		File file=new File(request.getParameter("sFile"));
		String fileName=file.getName();
		FtpTool ft=new FtpTool();
		try {
			ft.connect("/", "42.159.89.77", 21);
			ft.upload(file);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SqlOperator so=new SqlOperator();
		String sql="insert into kejian values('"+cid+"','"+fileName+"','"+title+"','"+explain+"','"+time+"')";
		System.out.println(sql);
		try {
			so.stat.execute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}

}
