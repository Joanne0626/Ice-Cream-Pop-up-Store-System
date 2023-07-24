package billing.domain;

public abstract class User {

	private String loginId;
	private String password;
	private String name;
	private String phoneNum;

	public User(String loginId,String password,String name,String phoneNum)
	{
		this.loginId=loginId;
		this.password=password;
		this.name=name;
		this.phoneNum=phoneNum;
	}
	
	public String getLoginId()
	{
		return loginId;
	}
	
	public void setLoginId(String loginId)
	{
		this.loginId=loginId;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum=phoneNum;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
}
