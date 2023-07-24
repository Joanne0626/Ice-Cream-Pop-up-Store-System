package billing.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private List<IceCream> cart;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private User user;
	private String orderId;
	
	public LocalDate getOrderDate()
	{
		return orderDate;
	}
	
	public void setOrderDate()
	{
		orderDate=LocalDate.now();
	}
	
	public LocalTime getOrderTime()
	{
		return orderTime;
	}
	
	public void setOrderTime()
	{
		orderTime=LocalTime.now();
	}
	
	public void setUser(User user)
	{
		this.user=user;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public String getOrderId()
	{
		return orderId;
	}
	
	public void setOrderId()
	{
		int index=(int)(Math.random()*(999+1));
		if(Math.floorDiv(index,10)==0)
			orderId="ORD00"+index;
		else if(Math.floorDiv(index,100)==0)
			orderId="ORD0"+index;
		else 
			orderId="ORD"+index;
	}
	
	public List<IceCream> getCart()
	{
		return cart;
	}
	
	public int getNumberOfTypesOfItemsInsideCart()
	{
		return cart.size();
	}
	
	public int getNumberOfItemsInsideCart()
	{
		int total=0;
		for(int i=0;i<cart.size();i++)
		{
			total+=cart.get(i).getQuantity();
		}
		return total;
	}
	
	public String[] getIceCreamTypes()
	{
		String[] types=new String[cart.size()];
		for(int i=0;i<cart.size();i++)
		{
			if(cart.get(i) instanceof Cone)
				types[i]="Cone";
			else if(cart.get(i) instanceof Sundae)
				types[i]="Sundae";
			else 
				types[i]=null;
		}
		return types;
	}
	
	public boolean checkTypeExist(IceCream iceCream, IceCream i)
	{
		if(iceCream instanceof Cone && i instanceof Cone)
		{
			if((iceCream.getId().equals(i.getId())) &&(iceCream.checkAddOn()==(i.checkAddOn())) && (((Cone)i).getFlavours().containsAll(((Cone)iceCream).getFlavours())==true))
				return true;
		}
		else if(iceCream instanceof Sundae && i instanceof Sundae)
		{
			if((iceCream.getId().equals(i.getId())) && (iceCream.checkAddOn()==(i.checkAddOn())) && (((Sundae) iceCream).getFlavour().equals(((Sundae) i).getFlavour())))
				return true;
		}
		return false;
	}
	
	public void addIceCream(IceCream iceCream)
	{
		cart.add(iceCream);
	}
	
	public void removeOrder(int choice)
	{
			cart.remove(choice-1);
	}
	
	public void updateOrder(IceCream iceCream,int choice)
	{
		cart.set(choice-1,iceCream);
	}
	
	public void placeOrder()
	{
		this.setOrderId();
		this.setOrderTime();
		this.setOrderDate();
	}
	
	public double getTotalPrice()
	{
		double total=0;
		for(int i=0;i<cart.size();i++)
		{
			total+=cart.get(i).getTotal();
		}
		return total;
	}
	
	public Order(User user)
	{
		this.user=user;
		cart=new ArrayList<IceCream>();
	}
	
}
