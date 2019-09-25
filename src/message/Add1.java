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
		String JDriver = "com.mysql.jdbc.Driver"; //定义JDBC驱动程序对象
	   	String userName="root";//数据库用户名
		String userPasswd="";//数据库存取密码
		String dbName="message";//数据库名
		String table1Name="stmessage";//数据库中的学生信息表
		String table2Name="admessage" ;//数据库中的管理员信息表
		String conURL="jdbc:mysql://localhost:3306/"+dbName;//连接数据库的URL
    	    try {
		     Class.forName(JDriver); //加载JDBC驱动程序
		     con=DriverManager.getConnection(conURL,userName,userPasswd); //连接数据库URL
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
		if(se.equals("女"))
			if(de.equals("软件学院"))
			    System.out.println("building");
		*/  //测试语句
		int buildnum=0,dornum=0,bednum=0;//buildnum为公寓楼号，dornum为宿舍号，bednum为床位号
	
		//对数据库表进行检索得到男生的数量count，女生的数量gcount
		try
		{
			
			Statement stm=con.createStatement();
			if(se.equals("男")){
			String sql="select * from stmessage where sex='男'";
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
			//测试语句
            if(se.equals("女")){
			String sql1="select * from stmessage where sex='女'";
            ResultSet re=stm.executeQuery(sql1);
            while(re.next())
            {
            	   String clas1=re.getString("clas");
         	       String department1=re.getString("department");
         	       if(clas1.equals(cl))
         		       if(department1.equals(de))
            	           gcount++;
            } 
            System.out.println(gcount);//测试语句
            }
		}catch(Exception e){e.printStackTrace();}
			
		//为学生分配宿舍
		if(se.equals("男")){
		    if(de.equals("软件学院"))
		    	buildnum=1;
		    else if(de.equals("数学学院"))
			    buildnum=2;
		    else if(de.equals("政法学院"))
			    buildnum=3;
		    else if(de.equals("信息学院"))
			    buildnum=4;
		    else if(de.equals("机械学院"))
		    	buildnum=5;
		    //System.out.println(buildnum);  //测试语句
		}
		else if(se.equals("女"))
		{
			if(de.equals("软件学院")||de.equals("数学学院"))
				buildnum=6;
			else if(de.equals("政法学院")||de.equals("信息学院"))
				buildnum=7;
			else if(de.equals("机械学院"))
				buildnum=8;
		}
		
		//初设5个系，每系男生360人，刚好一栋楼住下，每系女生180人，两系一栋楼；每系住三个年级
		if(se.equals("男")){
		    if(de.equals("软件学院"))
		    	buildnum=1;
		    else if(de.equals("数学学院"))
			    buildnum=2;
		    else if(de.equals("政法学院"))
			    buildnum=3;
		    else if(de.equals("信息学院"))
			    buildnum=4;
		    else if(de.equals("机械学院"))
		    	buildnum=5;
		}
		else if(se.equals("女"))
		{
			if(de.equals("软件学院")||de.equals("数学学院"))
				buildnum=6;
			else if(de.equals("政法学院")||de.equals("信息学院"))
				buildnum=7;
			else if(de.equals("机械学院"))
				buildnum=8;
		}
		if(se.equals("男"))
		{
			if(gr.equals("大学一年级"))
			{
				if(cl.equals("一班"))
				{
					dornum=1*100+count/4+1;
					bednum=count%4+1;
					
				}
				if(cl.equals("二班"))
				{
				    if(count<=32)    //二班在一楼有8个宿舍
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
				if(cl.equals("三班"))
				{
					dornum=2*100+count/4+1;
					bednum=count%4+1;
				}
			}
			if(gr.equals("大学二年级"))
			{
				if(cl.equals("一班"))
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
				if(cl.equals("二班"))
				{
				    	dornum=3*100+count/4+1;
				    	bednum=count%4+1;
				}
				if(cl.equals("三班"))
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
			if(gr.equals("大学三年级"))
			{
				if(cl.equals("一班"))
				{
				    	dornum=4*100+count/4+1;
					    bednum=count%4+1;
				}
				if(cl.equals("二班"))
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
				if(cl.equals("三班"))
				{  
				    	dornum=5*100+count/4+1;
					    bednum=count%4+1;
				}
			}
			
		}
		if(se.equals("女"))
		{
		    if(de.equals("软件学院")||de.equals("政法学院")||de.equals("机械学院"))
		    {
			    if(gr.equals("大学一年级"))
			    {
                    dornum=1*100+gcount/4+1;
				    bednum=gcount%4+1;
			    }
			    else if(gr.equals("大学二年级"))
			    {
			    	if(cl.equals("一班"))
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
				    if(cl.equals("二班")||cl.equals("三班"))
				    {
					    dornum=2*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
			    }
			    else if(gr.equals("大学三年级"))
			    {
				    if(cl.equals("一班"))
				    {
					    dornum=2*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
				    if(cl.equals("二班"))
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
				    if(cl.equals("三班"))
				    {
				    	dornum=3*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
			    }
			}
		    else if(de.equals("数学学院")||de.equals("信息学院"))
		    {
		    	if(gr.equals("大学一年级"))
		    	{
		    		if(cl.equals("一班"))
		    		{
		    			dornum=3*100+gcount/4+1;
					    bednum=gcount%4+1;
		    		}
				    if(cl.equals("二班"))
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
				    if(cl.equals("三班"))
				    {
				    	dornum=4*100+gcount/4+1;
					    bednum=gcount%4+1;
				    }
		    	}
		    	if(gr.equals("大学二年级"))
		    	{
		    		if(cl.equals("一班")||cl.equals("二班"))
		    		{
		    			dornum=4*100+gcount/4+1;
					    bednum=gcount%4+1;
		    		}
		    		if(cl.equals("三班"))
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
		    	if(gr.equals("大学三年级"))
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
       //将获得的信息装入数据库
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
            
			//将数据保存到StmessageDataBean中
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