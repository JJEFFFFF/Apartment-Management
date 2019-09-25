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
	 //�������ݿ�
	 public ViewStmessageServlet()
		{
		String JDriver = "com.mysql.jdbc.Driver"; //����JDBC�����������
	   	String userName="root";//���ݿ��û���
		String userPasswd="";//���ݿ��ȡ����
		String dbName="message";//���ݿ���
		String tableName="stmessage";//���ݿ��еı���
		String conURL="jdbc:mysql://localhost:3306/"+dbName;//�������ݿ��URL		 
			     try {
			     Class.forName(JDriver); //����JDBC��������
			     con=DriverManager.getConnection(conURL,userName,userPasswd); //�������ݿ�URL
			     }
			   catch(Exception e)
			     {  System.err.println(e.getMessage()); }
		}		
	 
	// �õ�GET���󣬴����ݿ��ж���������Ϣ
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
						//�����ݱ��浽StmessageDataBean��
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
		    //������ʾѧ����Ϣ��JSP
				request.setAttribute("messages",ret);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewStmessage.jsp");
			    requestDispatcher.forward(request,response);
			    
			}
			catch(Exception e){	e.printStackTrace();}
		    }
	}
