package billing.domain;

import java.util.List;

public class ConeList implements IConeList{

	private List<Cone> coneList;
	
	public ConeList()
	{
		this.setConeList();
	}
	
	public List<Cone> getConeList()
	{
		return coneList;
	}
	
	public void setConeList()
	{
		FileFunctionality coneFile=new FileFunctionality("cones.txt");
		coneList=coneFile.readConeFile();
	}
	
	public int getNumOfCones()
	{
		return coneList.size();
	}
	
	public boolean checkConeById(String id,Cone c)
	{
		if(id.equals(c.getId()))
			return true;
		else
			return false;
	}
	
	public boolean checkConeByNumOfScoops(int numOfScoops,Cone c)
	{
		if(c.getNumOfScoops()==numOfScoops)
			return true;
		else
			return false;
	}
	
	public void addCone(Cone c)
	{
		coneList.add(c);
	}
	
	public void editCone(int i,Cone c)
	{
		coneList.set(i,c);
	}
	
	public void deleteCone(String id)
	{
		for(int i=0;i<coneList.size();i++)
		{
			if(this.checkConeById(id, coneList.get(i))==true)
			{
				coneList.remove(i);
				return;
			}
		}
	}
}
