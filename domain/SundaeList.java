package billing.domain;

import java.util.List;

public class SundaeList implements ISundaeList{

	private List<Sundae> sundaeList;

	public SundaeList()
	{
		this.setSundaeList();
	}
	
	public List<Sundae> getSundaeList()
	{
		return sundaeList;
	}
	
	public void setSundaeList()
	{
		FileFunctionality sundaeFile=new FileFunctionality("sundaes.txt");
		sundaeList=sundaeFile.readSundaeFile();
	}
	
	public int getNumOfSundaes()
	{
		return sundaeList.size();
	}
	
	public boolean checkSundaeById(String id,Sundae s)
	{
		if(s.getId().equals(id))
			return true;
		else
			return false;
	}
	
	public boolean checkSundaeBySize(String size,Sundae s)
	{
		if(s.getSize().equals(size))
			return true;
		else
			return false;
	}
	
	public void addSundae(Sundae s)
	{
		sundaeList.add(s);
	}
	
	public void editSundae(int i,Sundae s)
	{
		sundaeList.set(i, s);
	}
	
	public void deleteSundae(String id)
	{
		for(int i=0;i<sundaeList.size();i++)
		{
			if(this.checkSundaeById(id, sundaeList.get(i))==true)
			{
				sundaeList.remove(i);
				return;
			}
		}
	}
	
}
