package billing.domain;

public class Client extends User {
	
	private String clientId;
	
	public Client(String loginId,String password,String name,String phoneNum,int indexNo)
	{
		super(loginId,password,name,phoneNum);
		this.setClientId(indexNo);
	}
	
	//for file input
	public Client(String loginId,String password,String name,String phoneNum,String clientId)
	{
		super(loginId,password,name,phoneNum);
		this.clientId=clientId;
	}
	
	public String getClientId()
	{
		return clientId;
	}
	
	public void setClientId(int indexNo)//for create new client
	{
		if(Math.floorDiv(indexNo, 10)==0)
			clientId="CL0000"+indexNo;
		else if(Math.floorDiv(indexNo, 100)==0)
			clientId="CL000"+indexNo;
		else if(Math.floorDiv(indexNo, 1000)==0)
			clientId="CL00"+indexNo;
		else if(Math.floorDiv(indexNo, 10000)==0)
			clientId="CL0"+indexNo;
		else if(Math.floorDiv(indexNo, 100000)==0)
			clientId="CL"+indexNo;
		else
			throw new IllegalArgumentException("Invalid indexNo.");
	}
	
	public void setClientId(String clientId)//for file input existing client
	{
		this.clientId=clientId;
	}

}
