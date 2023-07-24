package billing.domain;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentList {

	public List<BillingStatement> getPaymentList();
	public void setPaymentList();
	public int getNumOfPayments();
	public boolean checkPaymentByBillDate(LocalDate date,BillingStatement p);
	public List<BillingStatement> searchPaymentListByBillDate(LocalDate date);
	public boolean checkPaymentByUserId(String id,BillingStatement p);
	public List<BillingStatement> searchPaymentListByUserId(String id);
	public double getTotalRevenue(List<BillingStatement> p);
	public void addPayment(BillingStatement b);
	
}
