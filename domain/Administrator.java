package billing.domain;

public class Administrator extends User {

	private String adminId;
	
	public Administrator(String loginId,String password,String name,String phoneNum,int index)//for add admin
	{
		super(loginId,password,name,phoneNum);
		this.setAdminId(index);
	}

	//for file input
	public Administrator(String loginId,String password,String name,String phoneNum,String adminId)
	{
		super(loginId,password,name,phoneNum);
		this.adminId=adminId;	
	}
	
	public String getAdminId()
	{
		return adminId;
	}
	
	public void setAdminId(int indexNo)//for new admin
	{
		if(Math.floorDiv(indexNo, 10)==0)
			adminId="A00"+indexNo;
		else if(Math.floorDiv(indexNo, 100)==0)
			adminId="A0"+indexNo;
		else if(Math.floorDiv(indexNo, 1000)==0)
			adminId="A"+indexNo;
		else 
			throw new IllegalArgumentException("Invalid indexNo.");
	}
	
	public void setAdminId(String adminId)//for file input existing admin
	{
		this.adminId=adminId;
	}
	
}
