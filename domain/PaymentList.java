package billing.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentList implements IPaymentList{

	private List<BillingStatement> paymentList;
	
	public PaymentList()
	{
		this.setPaymentList();
	}
	
	public int getNumOfPayments()
	{
		return paymentList.size();
	}
	
	public void setPaymentList()
	{
		FileFunctionality billingFile=new FileFunctionality("payments.txt");
		paymentList=billingFile.readPaymentRecordsFile();
	}
	
	public List<BillingStatement> getPaymentList()
	{
		return paymentList;
	}
	
	public boolean checkPaymentByBillDate(LocalDate date,BillingStatement p)
	{
		if(p.getBillDate().equals(date))
		{
			return true;
		}
		else
			return false;
	}
	
	public List<BillingStatement> searchPaymentListByBillDate(LocalDate date)
	{
		List<BillingStatement> p=new ArrayList<BillingStatement>();
		for(int i=0;i<paymentList.size();i++)
		{
			if(this.checkPaymentByBillDate(date, paymentList.get(i))==true)
				p.add(paymentList.get(i));	
		}
		return p;
	}
	
	public boolean checkPaymentByUserId(String id,BillingStatement p)
	{
		if(p.getUserId().equals(id))
			return true;
		else
			return false;
	}
	
	public List<BillingStatement> searchPaymentListByUserId(String id)
	{
		List<BillingStatement> p=new ArrayList<BillingStatement>();
		for(int i=0;i<paymentList.size();i++)
		{
			if(this.checkPaymentByUserId(id, paymentList.get(i))==true)
				p.add(paymentList.get(i));	
		}
		return p;
	}
	
	public double getTotalRevenue(List<BillingStatement> p)
	{
		double total=0;
		for(int i=0;i<p.size();i++)
		{
			total+=p.get(i).getTotalPrice();
		}
		return total;
	}
	
	public void addPayment(BillingStatement b)
	{
		paymentList.add(b);
	}
}
