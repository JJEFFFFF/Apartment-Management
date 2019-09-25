package message;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;
 public class ViewAdmessageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
 {
	 private Connection con;
	 //�������ݿ�
	 public ViewAdmessageServlet()
		{
		String JDriver = "com.mysql.jdbc.Driver"; //����JDBC�����������
	   	String userName="root";//���ݿ��û���
		String userPasswd="";//���ݿ��ȡ����
		String dbName="message";//���ݿ���
		String tableName="admessage";//���ݿ��еı���
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
				ResultSet result=stm.executeQuery("select count(*) from  admessage");		
				int message_count=0;
				if(result.next())
				{
					message_count=result.getInt(1);
					result.close();			
				}
				if(message_count>0)
				{
					result=stm.executeQuery("select * from admessage");
					
					while(result.next())
					{ 
						
						String adnumber=result.getString("adnumber");
						String adname=result.getString("adname");
						String adsex=result.getString("adsex");
						String buildnum1=result.getString("buildnum1");
						
						//�����ݱ��浽StmessageDataBean��
						AdMessageDataBean message=new AdMessageDataBean();
						message.setAdnumber(adnumber);
						message.setAdname(adname);
						message.setAdsex(adsex);
						message.setBuildnum1(buildnum1);
						
						ret.add(message);						
					}
				result.close();
				stm.close();
				}
		    //������ʾѧ����Ϣ��JSP
				request.setAttribute("messages",ret);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ViewAdmessage.jsp");
			    requestDispatcher.forward(request,response);
			    
			}
			catch(Exception e){	e.printStackTrace();}
		    }
	}
