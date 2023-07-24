package billing.domain;

import java.util.List;

public class AdminList implements IAdminList{

	private List<Administrator> adminList;
	
	public AdminList()
	{
		this.setAdminList();
	}
	
	public List<Administrator> getAdminList()
	{
		return adminList;
	}
	
	public void setAdminList()
	{
		FileFunctionality adminFile=new FileFunctionality("admins.txt");
		adminList=adminFile.readAdminFile();
	}
	
	public int getNumOfAdmins()
	{
		return adminList.size();
	}
	
	public void addAdmin(Administrator a)
	{
			adminList.add(a);
	}
	
	
	public boolean searchAdminByLoginId(String id,Administrator a)
	{
		if(id.equals(a.getLoginId()))
			return true;
		else
			return false;
	}
}
