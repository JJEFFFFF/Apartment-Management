package message;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.*;

public class Add1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private Connection con;
	
	public Add1() {
		String JDriver = "com.mysql.jdbc.Driver"; //����JDBC�����������
	   	String userName="root";//���ݿ��û���
		String userPasswd="";//���ݿ��ȡ����
		String dbName="message";//���ݿ���
		String table1Name="stmessage";//���ݿ��е�ѧ����Ϣ��
		String table2Name="admessage" ;//���ݿ��еĹ���Ա��Ϣ��
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
	    byte b1[]=request.getParameter("name").getBytes("ISO-8859-1");
		String na=new String(b1);
		byte b2[]=request.getParameter("sex").getBytes("ISO-8859-1");
		String se=new String(b2);
		byte b3[]=request.getParameter("number").getBytes("ISO-8859-1");
		String nu=new String(b3);
		byte b4[]=request.getParameter("department").getBytes("ISO-8859-1");
		String de=new String(b4);
		byte b5[]=request.getParameter("grade").getBytes("ISO-8859-1");
		String gr=new String(b5);
		byte b6[]=request.getParameter("class").getBytes("ISO-8859-1");
		String cl=new String(b6);
		
		/*
		if(se.equals("Ů"))
			if(de.equals("���ѧԺ"))
			    System.out.println("building");
		*/  //�������
		int buildnum=0,dornum=0,bednum=0;//buildnumΪ��Ԣ¥�ţ�dornumΪ����ţ�bednumΪ��λ��
	
		//�����ݿ����м����õ�����������count��Ů��������gcount
		try
		{
			
			Statement stm=con.createStatement();
			if(se.equals("��")){
			String sql="select * from stmessage where sex='��'";
            ResultSet rs=stm.executeQuery(sql);
            while(rs.next())
            {
            	   String clas=rs.getString("clas");
            	   String department=rs.getString("department");
            	   if(clas.equals(cl))
            		   if(department.equals(de))
            	           count++;
            }
            System.out.println(count);
            }
			//�������
            if(se.equals("Ů")){
			String sql1="select * from stmessage where sex='Ů'";
            ResultSet re=stm.executeQuery(sql1);
            while(re.next())
            {
            	   String clas1=re.getString("clas");
         	       String department1=re.getString("department");
         	       if(clas1.equals(cl))
         		       if(department1.equals(de))
            	           gcount++;
            } 
            System.out.println(gcount);//�������
            }
		}catch(Exception e){e.printStackTrace();}
			
		//Ϊѧ����������
		if(se.equals("��")){
		    if(de.equals("���ѧԺ"))
		    	buildnum=1;
		    else if(de.equals("��ѧѧԺ"))
			    buildnum=2;
		    else if(de.equals("����ѧԺ"))
			    buildnum=3;
		    else if(de.equals("��ϢѧԺ"))
			    buildnum=4;
		    else if(de.equals("��еѧԺ"))
		    	buildnum=5;
		    //System.out.println(buildnum);  //�������
		}
		else if(se.equals("Ů"))
		{
			if(de.equals("���ѧԺ")||de.equals("��ѧѧԺ"))
				buildnum=6;
			else if(de.equals("����ѧԺ")||de.equals("��ϢѧԺ"))
				buildnum=7;
			else if(de.equals("��еѧԺ"))
				buildnum=8;
		}
		
		//����5��ϵ��ÿϵ����360�ˣ��պ�һ��¥ס�£�ÿϵŮ��180�ˣ���ϵһ��¥��ÿϵס�����꼶
		if(se.equals("��")){
		    if(de.equals("���ѧԺ"))
		    	buildnum=1;
		    else if(de.equals("��ѧѧԺ"))
			    buildnum=2;
		    else if(de.equals("����ѧԺ"))
			    buildnum=3;
		    else if(de.equals("��ϢѧԺ"))
			    buildnum=4;
		    else if(de.equals("��еѧԺ"))
		    	buildnum=5;
		}
		else if(se.equals("Ů"))
		{
			if(de.equals("���ѧԺ")||de.equals("��ѧѧԺ"))
				buildnum=6;
			else if(de.equals("����ѧԺ")||de.equals("��ϢѧԺ"))
				buildnum=7;
			else if(de.equals("��еѧԺ"))
				buildnum=8;
		}
		if(se.equals("��"))
		{
			if(gr.equals("��ѧһ�꼶"))
			{
				if(cl.equals("һ��"))
				{
					dornum=1*100+count/4+1;
					bednum=count%4+1;
					
				}
				if(cl.equals("����"))
				{
				    if(count<=32)    //������һ¥��8������
				    {
					    dornum=1*100+count/4+1;
					    bednum=count%4+1;
				    }
				    else
				    {
				    	dornum=2*100+count/4+1;
				    	bednum=count%4+1;
				    }
				}
				if(cl.equals("����"))
				{
					dornum=2*100+count/4+1;
					bednum=count%4+1;
				}
			}
			if(gr.equals("��ѧ���꼶"))
			{
				if(cl.equals("һ��"))
				{
				    if(count<=24)
				    {
					    dornum=2*100+count/4+1;
					    bednum=count%4+1;
				    }
				    else
				    {
				    	dornum=3*100+count/4+1;
					    bednum=count%4+1;
				    }
				}
				if(cl.equals("����"))
				{
				    	dornum=3*100+count/4+1;
				    	bednum=count%4+1;
				}
				if(cl.equals("����"))
				{
					if(count<=16)
				    {
					    dornum=3*100+count/4+1;
					    bednum=count%4+1;
				    }
				    else
				    {
				    	dornum=4*100+count/4+1;
					    bednum=count%4+1;
				    }
				}
			}
			if(gr.equals("��ѧ���꼶"))
			{
				if(cl.equals("һ��"))
				{
				    	dornum=4*100+count/4+1;
					    bednum=count%4+1;
				}
				if(cl.equals("����"))
				{
					if(count<=8)
				    {
					    dornum=4*100+count/4+1;
					    bednum=count%4+1;
				    }
				    else
				    {
				    	dornum=5*100+count/4+1;
					    bednum=count%4+1;
				    }
				}
				if(cl.equals("����"))
				{  
				    	dornum=5*100+count/4+1;
					    bednum=count%4+1;
				}
			}
			
		}
		if(se.equals("Ů"))
		{
		    if(de.equals("���ѧԺ")||de.equals("����ѧԺ")||de.equals("��еѧԺ"))
		    {
			    if(gr.equals("��ѧһ�꼶"))
			    {
                    dornum=1*100+gcount/4+1;
				    bednum=gcount%4+1;
			    }
			    else if(gr.equals("��ѧ���꼶"))
			    {
			    	if(cl.equals("һ��"))
			    	{
			    		if(gcount<=12)
			    		{
			    			dornum=1*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
					    else
					    {
						    dornum=2*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
				    }
				    if(cl.equals("����")||cl.equals("����"))
				    {
					    dornum=2*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
			    }
			    else if(gr.equals("��ѧ���꼶"))
			    {
				    if(cl.equals("һ��"))
				    {
					    dornum=2*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
				    if(cl.equals("����"))
				    {
					    if(gcount<=4)
					    {
						    dornum=2*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
					    else
					    {
						    dornum=3*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
				    }
				    if(cl.equals("����"))
				    {
				    	dornum=3*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
			    }
			}
		    else if(de.equals("��ѧѧԺ")||de.equals("��ϢѧԺ"))
		    {
		    	if(gr.equals("��ѧһ�꼶"))
		    	{
		    		if(cl.equals("һ��"))
		    		{
		    			dornum=3*100+gcount/4+1;
					    bednum=gcount%4+1;
		    		}
				    if(cl.equals("����"))
				    {
					    if(gcount<=16)
					    {
						    dornum=3*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
					    else
					    {
						    dornum=4*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
				    }
				    if(cl.equals("����"))
				    {
				    	dornum=4*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
		    	}
		    	if(gr.equals("��ѧ���꼶"))
		    	{
		    		if(cl.equals("һ��")||cl.equals("����"))
		    		{
		    			dornum=4*100+gcount/4+1;
					    bednum=gcount%4+1;
		    		}
		    		if(cl.equals("����"))
		    		{
		    			if(gcount<=8)
					    {
						    dornum=4*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
					    else
					    {
						    dornum=5*100+gcount/4+1;
						    bednum=gcount%4+1;
					    }
		    		}
		    	}
		    	if(gr.equals("��ѧ���꼶"))
		    	{
		    		dornum=5*100+gcount/4+1;
				    bednum=gcount%4+1;
		    	}
		    }
		}
		
		if(na==null)na="";
		if(se==null)se="";
		if(nu==null)nu="";
		if(de==null)de="";
		if(gr==null)gr="";
		if(cl==null)cl="";
		
		
		
		try
		 {
       //����õ���Ϣװ�����ݿ�
			PreparedStatement s=con.prepareStatement("insert into stmessage values(?,?,?,?,?,?,?,?,?)");
			s.setString(1,nu);
			s.setString(2,na);
			s.setString(3,se);	
			s.setString(4,de);
			s.setString(5,gr);
			s.setString(6,cl); 
			s.setInt(7,buildnum);
			s.setInt(8,dornum);
			s.setInt(9,bednum);
			try 
			  {	s.execute();	} 
			catch(Exception e){ 	}			
            
			//�����ݱ��浽StmessageDataBean��
			Collection ret=new ArrayList();
			StMessageDataBean message=new StMessageDataBean();
			message.setNumber(nu);
			message.setName(na);
			message.setSex(se);
			message.setDepartment(de);
			message.setGrade(gr);
			message.setClas(cl);
			message.setBuildnum(buildnum);
			message.setDornum(dornum);
			message.setBednum(bednum);
			ret.add(message);
			
			request.setAttribute("messages",ret);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("ViewStmessage.jsp");
		    requestDispatcher.forward(request,response);
	    	
		 }
		   catch(Exception e){e.printStackTrace();}                                                        
		}
}