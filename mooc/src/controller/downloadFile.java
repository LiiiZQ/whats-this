package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.FtpTool;

/*
 *张钰焯、杨海洋------------------下载老师上传的课件
 */
@WebServlet("/downloadFile")
public class downloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName=request.getParameter("fileName");
		FtpTool ft=new FtpTool();
		try {
			ft.connect("/", "42.159.89.77", 21);
			System.out.println("下载中");
			ft.downFile("/", fileName, "D:\\");
			System.out.println("下载完成");
			request.getRequestDispatcher("stCourse.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
