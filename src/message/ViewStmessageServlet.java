 package message;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;
 public class ViewStmessageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
 {
	 private Connection con;
	 //连接数据库
	 public ViewStmessageServlet()
		{
		String JDriver = "com.mysql.jdbc.Driver"; //定义JDBC驱动程序对象
	   	String userName="root";//数据库用户名
		String userPasswd="";//数据库存取密码
		String dbName="message";//数据库名
		String tableName="stmessage";//数据库中的表名
		String conURL="jdbc:mysql://localhost:3306/"+dbName;//连接数据库的URL		 
			     try {
			     Class.forName(JDriver); //加载JDBC驱动程序
			     con=DriverManager.getConnection(conURL,userName,userPasswd); //连接数据库URL
			     }
			   catch(Exception e)
			     {  System.err.println(e.getMessage()); }
		}		
	 
	// 得到GET请求，从数据库中读出留言信息
	 public void doGet(HttpServletRequest request,HttpServletResponse response)
	        throws IOException, ServletException
	    {
		 doPost(request,response);
	    }  
	   	public void doPost(HttpServletRequest request,HttpServletResponse response)
		        throws IOException, ServletException
		    {  	
	   		Collection ret=new ArrayList();
			try
			{
				Statement stm=con.createStatement();
				ResultSet result=stm.executeQuery("select count(*) from  stmessage");		
				int message_count=0;
				if(result.next())
				{
					message_count=result.getInt(1);
					result.close();			
				}
				if(message_count>0)
				{
					result=stm.executeQuery("select * from stmessage order by buildnum asc");
					
					while(result.next())
					{ 
						
						String number=result.getString("number");
						String name=result.getString("name");
						String sex=result.getString("sex");
						String department=result.getString("department");
						String grade=result.getString("grade");
						String clas=result.getString("clas");
						int buildnum=result.getInt("buildnum");
						int dornum=result.getInt("dornum");
						int bednum=result.getInt("bednum");
						//将数据保存到StmessageDataBean中
						StMessageDataBean message=new StMessageDataBean();
						message.setNumber(number);
						message.setName(name);
						message.setSex(sex);
						message.setDepartment(department);
						message.setGrade(grade);
						message.setClas(clas);
						message.setBuildnum(buildnum);
						message.setDornum(dornum);
						message.setBednum(bednum);
						ret.add(message);						
					}
				result.close();
				stm.close();
				}
		    //访问显示学生信息的JSP
				request.setAttribute("messages",ret);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewStmessage.jsp");
			    requestDispatcher.forward(request,response);
			    
			}
			catch(Exception e){	e.printStackTrace();}
		    }
	}
