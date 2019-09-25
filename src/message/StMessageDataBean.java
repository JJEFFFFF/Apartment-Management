package message;

public class StMessageDataBean {
	private String number,name,sex,department,grade,clas;
	private int buildnum,dornum,bednum;
	//setter或者getter方法
	public void setNumber(String number)
	{	this.number=number;		}
	public void setName(String name)
	{	this.name=name;	}
	public void setSex(String sex)
	{	this.sex=sex;	}
	public void setDepartment(String department)
	{	this.department=department;	}
	public void setGrade(String grade)
	{	this.grade=grade;	}
	public void setClas(String clas)
	{	this.clas=clas;	}
	public void setBuildnum(int buildnum)
	{	this.buildnum=buildnum;	}
	public void setDornum(int dornum)
	{	this.dornum=dornum;	}
	public void setBednum(int bednum)
	{	this.bednum=bednum;	}
	
	public String getNumber()
	{	return this.number;	}
	public String getName()
	{	return this.name;	}
	public String getSex()
	{	return this.sex;	}
	public String getDepartment()
	{	return this.department;	}
	public String getGrade()
	{	return this.grade;	}
	public String getClas()
	{	return this.clas;	}
	public int getBuildnum()
	{	return this.buildnum;	}
	public int getDornum()
	{	return this.dornum;	}
	public int getBednum()
	{	return this.bednum;	}

}
