package billing.domain;

import java.util.ArrayList;
import java.util.List;

public class Cone extends IceCream {

	private int numOfScoops;
	private List<Flavour> flavours;
	
	public Cone(int numOfScoops,double price,int index)//For add new cone
	{
		super(price);
		this.setNumOfScoops(numOfScoops);
		this.setId(index);
		flavours=new ArrayList<Flavour>();
	}
	
	public Cone(String id,int numOfScoops,double price)//For read Cone file
	{
		super(price);
		this.setId(id);
		this.numOfScoops=numOfScoops;
		flavours=new ArrayList<Flavour>();
	}
	
	public Cone()
	{
		
	}
	
	public void setId(int indexNo)
	{
		if(Math.floorDiv(indexNo,10)==0)
			this.setId("C0"+indexNo);
		else if(Math.floorDiv(indexNo, 100)==0)
			this.setId("C"+indexNo);
		else
			throw new IllegalArgumentException("Invalid indexNo"); 
	}
	
	public int getNumOfScoops()
	{
		return numOfScoops;
	}
	
	public void setNumOfScoops(int numOfScoops)
	{
		this.numOfScoops=numOfScoops;
	}
	
	public void setFlavours(Flavour f)
	{
		flavours.add(f);
	}
	
	public List<Flavour> getFlavours()
	{
		return flavours;
	}
}
