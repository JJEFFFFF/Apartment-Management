package message;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;

public class AddAd1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private Connection con;
	
	public AddAd1() {
		String JDriver = "com.mysql.jdbc.Driver"; //����JDBC�����������
	   	String userName="root";//���ݿ��û���
		String userPasswd="";//���ݿ��ȡ����
		String dbName="message";//���ݿ���
		String tableName="admessage" ;//���ݿ��еĹ���Ա��Ϣ��
		String conURL="jdbc:mysql://localhost:3306/"+dbName;//�������ݿ��URL
    	    try {
		     Class.forName(JDriver); //����JDBC��������
		     con=DriverManager.getConnection(conURL,userName,userPasswd); //�������ݿ�URL
		     }
		   catch(Exception e) 
		     {  System.err.println(e.getMessage()); }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    byte b1[]=request.getParameter("adnumber").getBytes("ISO-8859-1");
		String adnum=new String(b1);
		byte b2[]=request.getParameter("adname").getBytes("ISO-8859-1");
		String adna=new String(b2);
		System.out.println(adna);
		byte b3[]=request.getParameter("adsex").getBytes("ISO-8859-1");
		String adse=new String(b3);
		byte b4[]=request.getParameter("buildnum1").getBytes("ISO-8859-1");
		String bu=new String(b4);
		byte b5[]=request.getParameter("passwd").getBytes("ISO-8859-1");
		String pa=new String(b5);
		
		if(adnum==null)adnum="";
		if(adna==null)adna="";
		if(adse==null)adse="";
		if(bu==null)bu="";
		if(pa==null)pa="";
		
		
		
		try
		 {
       //����õ���Ϣװ�����ݿ�
			PreparedStatement s=con.prepareStatement("insert into admessage values(?,?,?,?,?)");
			s.setString(1,adnum);
			s.setString(2,adna);
			s.setString(3,adse);	
			s.setString(4,bu);
			s.setString(5,pa);

			try 
			  {	s.execute();	} 
			catch(Exception e){ 	}			
            
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("ViewAdmessage.jsp");
		    requestDispatcher.forward(request,response);
	    	
		 }
		   catch(Exception e){e.printStackTrace();}                                                        
		}
}