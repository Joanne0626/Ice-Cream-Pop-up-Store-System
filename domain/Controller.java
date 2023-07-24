package billing.domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {

		private IAdminList adminList;
		private IClientList clientList;
		private IConeList coneMenu;
		private ISundaeList sundaeMenu;
		private IFlavourList flavourMenu;
		private IPaymentList paymentRecords;
		private Order order;
		private BillingStatement bill;
		private User loginAccount;
		
		public Controller()
		{
			adminList=new AdminList();
			clientList=new ClientList();
			coneMenu=new ConeList();
			sundaeMenu=new SundaeList();
			flavourMenu=new FlavourList();
			paymentRecords=new PaymentList();
		}
		
		//Client
		public void registerClient(String loginId,String password, String name,String phoneNum)
		{
			int indexNo=clientList.getNumOfClients()+1;
			Client client=new Client(loginId,password,name,phoneNum,indexNo);
			clientList.addClient(client);
		}
		
		public boolean checkClientLogin(String loginId,String password)
		{
			for(int i=0;i<clientList.getNumOfClients();i++)
			{
				if(loginId.equals(clientList.getClientList().get(i).getLoginId()) && password.equals(clientList.getClientList().get(i).getPassword()))
				{
					loginAccount=clientList.getClientList().get(i);
					order=new Order(loginAccount);
					return true;
				}
			}
			return false;
		}
		
		public boolean searchClientByLoginId(String loginId)
		{
			for(int i=0;i<clientList.getNumOfClients();i++)
			{
				if(clientList.searchClientByLoginId(loginId, clientList.getClientList().get(i))==true)
					return true;
			}
			return false;
		}
		
		public List<Client> getClientList()
		{
			return clientList.getClientList();
		}
		
		public int getNumberOfClients()
		{
			return clientList.getNumOfClients();
		}
		
		//admin
		public void registerAdmin(String loginId,String password, String name, String phoneNum)
		{
			int indexNo=adminList.getNumOfAdmins()+1;
			Administrator admin=new Administrator(loginId,password,name,phoneNum,indexNo);
			adminList.addAdmin(admin);
		}
		
		public boolean checkAdminLogin(String loginId,String password)
		{
			for(int i=0;i<adminList.getNumOfAdmins();i++)
			{
				if(loginId.equals(adminList.getAdminList().get(i).getLoginId()) && password.equals(adminList.getAdminList().get(i).getPassword()))
				{
					loginAccount=adminList.getAdminList().get(i);
					order=new Order(loginAccount);
					return true;
				}
			}
			return false;
		}

		public List<Administrator> getAdminList()
		{
			return adminList.getAdminList();
		}
		
		public int getNumberOfAdmins()
		{
			return adminList.getNumOfAdmins();
		}
		
		public boolean searchAdminByLoginId(String loginId)
		{
			for(int i=0;i<adminList.getNumOfAdmins();i++)
			{
				if(adminList.searchAdminByLoginId(loginId, adminList.getAdminList().get(i)))
					return true;
			}
			return false;
		}
		
		//Cone
		public void addConeToMenu(int numOfScoops,double price)
		{
			int indexNo=coneMenu.getNumOfCones()+1;
			Cone cone=new Cone(numOfScoops,price,indexNo);
			coneMenu.addCone(cone);
		}
		
		public void editConeInMenu(int numOfScoops,double price,String id)
		{
			for(int i=0;i<coneMenu.getNumOfCones();i++)
			{
				if(coneMenu.checkConeById(id, coneMenu.getConeList().get(i))==true)
				{
					Cone cone=new Cone(id,numOfScoops,price);
					coneMenu.editCone(i,cone);
				}
			}
		}
		
		public void deleteConeFromMenu(String id)
		{
			coneMenu.deleteCone(id);
		}
		
		public Cone getConeById(String id)
		{
			Cone cone=new Cone();
			for(int i=0;i<coneMenu.getNumOfCones();i++)
			{
				if(coneMenu.checkConeById(id, coneMenu.getConeList().get(i))==true)
				{
					cone=coneMenu.getConeList().get(i);
					return cone;
				}
			}
			return null;
		}
		
		public void addConeToOrder(Cone c,int quantity,int addOption, List<Flavour> flavours)
		{
			c.setQuantity(quantity);
			if(addOption==1)
				c.addOn();
			for(int i=0;i<flavours.size();i++)
			{
				c.setFlavours(flavours.get(i));
			}
			IceCream iceCream=c;
			order.addIceCream(iceCream);
		}
		
		public boolean checkConeByNumOfScoops(int numOfScoops)
		{
			for(int i=0;i<coneMenu.getNumOfCones();i++)
			{
				if(coneMenu.checkConeByNumOfScoops(numOfScoops, coneMenu.getConeList().get(i)))
					return true;
			}
			return false;
		}
		
		public boolean checkConeById(String id)
		{
			for(int i=0;i<coneMenu.getNumOfCones();i++)
			{
				if(coneMenu.checkConeById(id, coneMenu.getConeList().get(i)))
					return true;
			}
			return false;
		}
		
		public List<Cone> getConeMenu()
		{
			return coneMenu.getConeList();
		}
		
		public int getNumberOfCones()
		{
			return coneMenu.getNumOfCones();
		}
		
		//Sundae
		public void addSundaeToMenu(String size,double price)
		{
			int indexNo=sundaeMenu.getNumOfSundaes()+1;
			Sundae sundae=new Sundae(size,price,indexNo);
			sundaeMenu.addSundae(sundae);
		}
		
		public void editSundaeInMenu(String size,double price,String id)
		{
			for(int i=0;i<sundaeMenu.getNumOfSundaes();i++)
			{
				if(sundaeMenu.checkSundaeById(id, sundaeMenu.getSundaeList().get(i))==true)
				{
					Sundae sundae=new Sundae(id,size,price);
					sundaeMenu.editSundae(i,sundae);
				}
			}
		}
		
		public void deleteSundaeFromMenu(String id)
		{
			sundaeMenu.deleteSundae(id);
		}
		
		public List<Sundae> getSundaeMenu()
		{
			return sundaeMenu.getSundaeList();
		}
		
		public int getNumberOfSundaes()
		{
			return sundaeMenu.getNumOfSundaes();
		}
		
		public Sundae getSundaeById(String id)
		{
			for(int i=0;i<sundaeMenu.getNumOfSundaes();i++)
			{
				if(sundaeMenu.checkSundaeById(id, sundaeMenu.getSundaeList().get(i))==true)
				{
					return sundaeMenu.getSundaeList().get(i);
				}
			}
			return null;
		}
		
		public boolean checkSundaeBySize(String size)
		{
			for(int i=0;i<sundaeMenu.getNumOfSundaes();i++)
			{
				if(sundaeMenu.checkSundaeBySize(size, sundaeMenu.getSundaeList().get(i)))
					return true;
			}
			return false;
		}
		
		public boolean checkSundaeById(String id)
		{
			for(int i=0;i<sundaeMenu.getNumOfSundaes();i++)
			{
				if(sundaeMenu.checkSundaeById(id, sundaeMenu.getSundaeList().get(i)))
					return true;
			}
			return false;
		}
		
		public void addSundaeToOrder(Sundae s,int quantity,int addOption, Flavour flavour)
		{
			s.setQuantity(quantity);
			if(addOption==1)
				s.addOn();
			s.setFlavour(flavour);
			IceCream iceCream=s;
			order.addIceCream(iceCream);
		}
		
		//Flavour
		public void addFlavourToMenu(String name)
		{
			int indexNo=flavourMenu.getNumOfFlavours()+1;
			Flavour flavour=new Flavour(name,indexNo);
			flavourMenu.addFlavour(flavour);
		}
		
		public void editFlavourInMenu(String name,String id)
		{
			for(int i=0;i<flavourMenu.getNumOfFlavours();i++)
			{
				if(flavourMenu.checkFlavourById(id, flavourMenu.getFlavourList().get(i))==true)
				{
					Flavour flavour=new Flavour(id,name);
					flavourMenu.editFlavour(i,flavour);
				}
			}
		}
		
		public void deleteFlavourFromMenu(String id)
		{
			flavourMenu.deleteFlavour(id);
		}
		
		public List<Flavour> getFlavourMenu()
		{
			return flavourMenu.getFlavourList();
		}
		
		public int getNumberOfFlavours()
		{
			return flavourMenu.getNumOfFlavours();
		}
		
		public Flavour getFlavourById(String id)
		{
			for(int i=0;i<flavourMenu.getNumOfFlavours();i++)
			{
				if(flavourMenu.checkFlavourById(id, flavourMenu.getFlavourList().get(i)))
					return flavourMenu.getFlavourList().get(i);
			}
			return null;
		}
		
		public boolean checkFlavourByName(String name)
		{
			for(int i=0;i<flavourMenu.getNumOfFlavours();i++)
			{
				if(flavourMenu.checkFlavourByName(name, flavourMenu.getFlavourList().get(i)))
					return true;
			}
			return false;
		}
		
		public boolean checkFlavourById(String id)
		{
			for(int i=0;i<flavourMenu.getNumOfFlavours();i++)
			{
				if(flavourMenu.checkFlavourById(id, flavourMenu.getFlavourList().get(i)))
					return true;
			}
			return false;
		}
		
		//Order
		public Order getOrder()
		{
			return order;
		}
		
		public void resetOrder()
		{
			order=new Order(loginAccount);
		}
		
		public void placeOrder()
		{
			order.placeOrder();
			int index=paymentRecords.getNumOfPayments()+1;
			bill=new BillingStatement(order,index);
		}
		
		public void updateOrder(int choice,int quantity)
		{
			IceCream i=order.getCart().get(choice-1);
			i.setQuantity(quantity);
			order.updateOrder(i, choice);
		}
		
		public void removeOrder(int choice)
		{
			order.removeOrder(choice);
		}
		
		//loginAccount
		public User getLoginAccount()
		{
			return loginAccount;
		}
		
		public void resetLoginAccount()
		{
			loginAccount=null;
		}
		
		//Billing Statement
		public BillingStatement getBillingStatement()
		{
			return bill;
		}
		
		//Payment History
		public List<BillingStatement> getPaymentRecords()
		{
			return paymentRecords.getPaymentList();
		}
		
		public int getNumberOfPayments()
		{
			return paymentRecords.getNumOfPayments();
		}
		
		public void addPaymentRecord()
		{
			paymentRecords.addPayment(bill);
		}
		
		public List<BillingStatement> getPaymentRecordsByBillDate(String date)
		{
			DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate billDate=LocalDate.parse(date,dateformat);
			return paymentRecords.searchPaymentListByBillDate(billDate);
		}
		
		public List<BillingStatement> getPaymentRecordsByUserId(String id)
		{
			return paymentRecords.searchPaymentListByUserId(id);
		}
		
		public void resetPaymentRecords()
		{
			paymentRecords.setPaymentList();
		}
		
		public double getTotalRevenue(List<BillingStatement> p)
		{
			return paymentRecords.getTotalRevenue(p);
		}
	
}

