package billing.domain;


public class Sundae extends IceCream{
	
	private String size;
	private Flavour flavour;

	public Sundae(String size,double price,int index)//For add new sundae
	{
		super(price);
		this.setSize(size);
		this.setId(index);
	}
	
	public Sundae(String id,String size,double price)//For read existing sundae
	{
		super(price);
		this.setId(id);
		this.size=size;
	}
	
	public void setId(int indexNo)
	{
		if(Math.floorDiv(indexNo,10)==0)
			this.setId("S0"+indexNo);
		else if(Math.floorDiv(indexNo, 100)==0)
			this.setId("S"+indexNo);
		else
			throw new IllegalArgumentException("Invalid indexNo"); 
	}
	
	public String getSize()
	{
		return size;
	}
	
	public void setSize(String size)
	{
		this.size=size;
	}

	public void setFlavour(Flavour f)
	{
		flavour=f;
	}
	
	public Flavour getFlavour()
	{
		return flavour;
	}
}
