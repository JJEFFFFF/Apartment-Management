package message;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;

public class visadd extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private Connection con;
	
	public visadd() {
		String JDriver = "com.mysql.jdbc.Driver"; //����JDBC�����������
	   	String userName="root";//���ݿ��û���
		String userPasswd="";//���ݿ��ȡ����
		String dbName="message";//���ݿ���
		String tableName="vismessage";//���ݿ��е�ѧ����Ϣ��
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
		int count=0,gcount=0;


		
		//request.setCharacterEncoding("GB2312");
	    byte b1[]=request.getParameter("number").getBytes("ISO-8859-1");
		String nu=new String(b1);
		byte b2[]=request.getParameter("date").getBytes("ISO-8859-1");
		String da=new String(b2);
		byte b3[]=request.getParameter("name").getBytes("ISO-8859-1");
		String na=new String(b3);
		byte b4[]=request.getParameter("time1").getBytes("ISO-8859-1");
		String t1=new String(b4);
		byte b5[]=request.getParameter("time2").getBytes("ISO-8859-1");
		String t2=new String(b5);
		byte b6[]=request.getParameter("thing").getBytes("ISO-8859-1");
		String th=new String(b6);
		byte b7[]=request.getParameter("other").getBytes("ISO-8859-1");
		String ot=new String(b7);
		
		
		
		if(nu==null)nu="";
		if(da==null)da="";
		if(na==null)na="";
		if(t1==null)t1="";
		if(t2==null)t2="";
		if(th==null)th="";
		if(ot==null)ot="";
		
		
		try
		 {
       //����õ���Ϣװ�����ݿ�
			PreparedStatement s=con.prepareStatement("insert into stmessage values(?,?,?,?,?,?,?)");
			s.setString(1,nu);
			s.setString(2,da);
			s.setString(3,na);	
			s.setString(4,t1);
			s.setString(5,t2);
			s.setString(6,th); 
			s.setString(7,ot);
			
			try 
			  {	s.execute();	} 
			catch(Exception e){ 	}			
            
			//�����ݱ��浽StmessageDataBean��
			Collection ret=new ArrayList();
			VismessageDataBean message=new VismessageDataBean();
			message.setNumber(nu);
			message.setDate(da);
			message.setName(na);
			message.setTime1(t1);
			message.setTime2(t2);
			message.setThing(th);
			message.setOther(ot);
			ret.add(message);
			
			request.setAttribute("messages",ret);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("ViewVismessage.jsp");
		    requestDispatcher.forward(request,response);
	    	
		 }
		   catch(Exception e){e.printStackTrace();}                                                        
		}
}