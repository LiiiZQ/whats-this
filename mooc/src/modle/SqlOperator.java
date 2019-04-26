package modle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlOperator {

	private String sql;

	private static String user="root";
	//private static String user="nozzn3xlwl";

	private static String pwd="woshi3bwopashei";
	//private static String pwd="0301kw1lm3ihxlwyyxm4kwh1h02k15klzw2myij2";
	private static String url="jdbc:mysql://localhost:3306/shixun?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
	//private static String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_iterykt";

	private static String driver = "com.mysql.cj.jdbc.Driver";
	//private static String driver ="com.mysql.jdbc.Driver";

	public Connection con;
	public Statement stat;
	public ResultSet rs;

	public void close() {
		try {
			if(con!=null&&!con.isClosed()) {
				con.close();
			}
			if(stat!=null&&!stat.isClosed()) {
				stat.close();
			}
		} catch (SQLException e) {
			// TODO 
			e.printStackTrace();
		}
	}
	

	public int insert(String[] info) {
		
		try {
			sql=String.format("INSERT INTO user VALUES('%s','%s','%s','%s')", info[0],info[1],info[2],info[3]);
			rs=stat.executeQuery(sql);
			return rs.getRow();
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}		
	}
	

	public ResultSet getRS() {
		try {
			sql="SELECT * FROM user natural join info";
			rs=stat.executeQuery(sql);
			return rs;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public SqlOperator(){

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);

			stat=con.createStatement();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
