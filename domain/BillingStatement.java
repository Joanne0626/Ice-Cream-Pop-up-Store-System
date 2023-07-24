package billing.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class BillingStatement {

	private Order order;
	private String userId;
	private double totalPrice;
	private boolean paid;
	private String method;
	private double paidAmount;
	private double returnAmount;
	private String billRef;
	private LocalDate billDate;
	private LocalTime billTime;
	
	public BillingStatement(String billRef,LocalDate billDate,LocalTime billTime,String userId,String method,double totalPrice)
	{
		this.billRef=billRef;
		this.billDate=billDate;
		this.billTime=billTime;
		this.userId=userId;
		this.method=method;
		this.totalPrice=totalPrice;
	}
	
	public BillingStatement(Order o,int indexNo)
	{
		order=o;
		paid=false;
		this.setBillRef(indexNo);
		this.setUserId();
		this.totalPrice=o.getTotalPrice();
	}
	
	public void processPayment(String method,double amount)
	{
		if(amount>=totalPrice)
		{
			paidAmount=amount;
			returnAmount=paidAmount-totalPrice;
			paid=true;
			this.method=method;
			this.setBillDate();
			this.setBillTime();
		}
	}
	
	public boolean checkPaid()
	{
		return paid;
	}
	
	public Order getOrder()
	{
		return order;
	}
	
	public void setTotalPrice()
	{
		totalPrice=order.getTotalPrice();
	}
	
	public double getTotalPrice()
	{
		return totalPrice;
	}
	
	public void setUserId()
	{
		if(order.getUser() instanceof Client)
			userId=((Client)order.getUser()).getClientId();
		else if(order.getUser() instanceof Administrator)
			userId=((Administrator)order.getUser()).getAdminId();
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getMethod()
	{
		return method;
	}
	
	public double getPaidAmount()
	{
		return paidAmount;
	}
	
	public double getReturnAmount()
	{
		return returnAmount;
	}
	
	public void setBillRef(int index)
	{
		if(Math.floorDiv(index, 10)==0)
			billRef="B0000"+index;
		else if(Math.floorDiv(index, 100)==0)
			billRef="B000"+index;
		else if(Math.floorDiv(index, 1000)==0)
			billRef="B00"+index;
		else if(Math.floorDiv(index, 10000)==0)
			billRef="B0"+index;
		else if(Math.floorDiv(index, 100000)==0)
			billRef="B"+index;
		else
			throw new IllegalArgumentException("Invalid Index No");
	}
	
	public String getBillRef()
	{
		return billRef;
	}
	
	public void setBillDate()
	{
		billDate=LocalDate.now();
	}
	
	public LocalDate getBillDate()
	{
		return billDate;
	}
	
	public void setBillTime()
	{
		billTime=LocalTime.now();
	}
	
	public LocalTime getBillTime()
	{
		return billTime;
	}
}
