package billing.domain;

public class Flavour {

	private String id;
	private String name;
	
	public Flavour(String id,String name)
	{
		this.id=id;
		this.name=name;
	}
	
	public Flavour(String name,int index)
	{
		this.name=name;
		this.setId(index);
	}
	
	public void setId(String id)
	{
		this.id=id;
	}
	
	public void setId(int index)
	{
		if(Math.floorDiv(index, 10)==0)
			id="F0"+index;
		else if(Math.floorDiv(index,100)==0)
			id="F"+index;
		else
			throw new IllegalArgumentException("Invalid Index No");
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
}
