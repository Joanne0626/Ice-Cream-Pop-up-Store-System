package billing.domain;

public abstract class IceCream {

	private String id;
	private double price;
	private boolean addOn; 
	private int quantity;
	
	public IceCream(double price)//For menu
	{
		this.setPrice(price);
		quantity=0;
		addOn=false;
	}
	
	public IceCream()
	{
		
	}
	
	public String getId()
	{
		return id;
	}
	
	public abstract void setId(int indexNo);
	
	public void setId(String id)
	{
		this.id=id;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	public boolean checkAddOn()
	{
		return addOn;
	}
	
	public void addOn()
	{
		addOn=true;
		price+=0.5;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public double getTotal()
	{
		return quantity*price;
	}
	
}
