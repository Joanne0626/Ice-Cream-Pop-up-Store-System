package billing.domain;

import java.util.List;

public class FlavourList implements IFlavourList{

	private List<Flavour> flavourList;
	
	public FlavourList()
	{
		this.setFlavourList();
	}
	
	public List<Flavour> getFlavourList()
	{
		return flavourList;
	}
	
	public void setFlavourList()
	{
		FileFunctionality flavourFile=new FileFunctionality("flavours.txt");
		flavourList=flavourFile.readFlavourFile();
	}
	
	public int getNumOfFlavours()
	{
		return flavourList.size();
	}
	
	public boolean checkFlavourById(String id,Flavour f)
	{
		if(f.getId().equals(id))
			return true;
		else 
			return false;
	}
	
	public boolean checkFlavourByName(String name,Flavour f)
	{
		if(f.getName().equals(name))
			return true;
		else 
			return false;
	}
	
	public void addFlavour(Flavour f)
	{
		flavourList.add(f);
	}
	
	public void editFlavour(int i,Flavour f)
	{
		flavourList.set(i, f);
	}
	
	public void deleteFlavour(String id)
	{
		for(int i=0;i<flavourList.size();i++)
		{
			if(this.checkFlavourById(id, flavourList.get(i))==true)
			{
				flavourList.remove(i);
				return;
			}
		}
	}
}
