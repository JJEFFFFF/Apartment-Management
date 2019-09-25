package message;

public class AdMessageDataBean {
	
	//管理员的setter和getter方法
	private String adnumber,adname,adsex,buildnum1;
	public void setAdnumber(String adnumber)
	{	this.adnumber=adnumber;	}
	public void setAdname(String adname)
	{	this.adname=adname;	}
	public void setAdsex(String adsex)
	{	this.adsex=adsex;	}
	public void setBuildnum1(String buildnum1)
	{	this.buildnum1=buildnum1;	}
	
	public String getAdnumber()
	{	return this.adnumber;	}
	public String getAdname()
	{	return this.adname;	}
	public String getAdsex()
	{	return this.adsex;	}
	public String getBuildnum1()
	{	return this.buildnum1;	}
}
