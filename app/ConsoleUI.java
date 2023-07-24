package billing.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import billing.domain.*;

public class ConsoleUI {

	private Scanner scanner;
	private Controller controller;
	
	public ConsoleUI(Controller controller) 
	{
		scanner = new Scanner(System.in);
		this.controller = controller;
	}
	
	public void start()
	{
		int choice;
		do 
		{
			System.out.println("Welcome to Cool Fresh Ice Cream Store: ");
			System.out.println("1. Login As Customer.");
			System.out.println("2. Register Customer Profile.");
			System.out.println("3. Login As Admin.");
			System.out.println("4. Turn Off System.");
			System.out.print("Enter your choice (1-4): ");
			choice=scanner.nextInt();
			System.out.println();
			
			while (choice < 1 || choice > 4) 
			{
				System.out.println("Invalid choice. Please try again.");
				System.out.println();
				System.out.println("Welcome to Cool Fresh Ice Cream Store: ");
				System.out.println("1. Login As Customer.");
				System.out.println("2. Register Customer Profile.");
				System.out.println("3. Login As Admin.");
				System.out.println("4. Turn Off System.");
				System.out.print("Enter your choice (1-4): ");
				choice = scanner.nextInt();
				System.out.println();
			}
			
			switch(choice)
			{
				case 1: loginCustomerProfile(); 
						break;
				case 2: registerCustomerProfile();
						break;
				case 3: loginAdminProfile();
						break;
				case 4: return;
			}
		}while(choice!=4);
	}
	
	public void loginCustomerProfile()
	{
		System.out.print("Login ID: ");
		String loginId=scanner.next();
		System.out.print("Password: ");
		String skip=scanner.nextLine();
		String password=scanner.nextLine();
		boolean login=controller.checkClientLogin(loginId, password);
		while(login!=true)
		{
			System.out.println("Incorrect loginID or password !");
			System.out.println("Please try again");
			System.out.println();
			System.out.print("Login ID: ");
			loginId=scanner.next();
			System.out.print("Password: ");
			skip=scanner.nextLine();
			password=scanner.nextLine();
			login=controller.checkClientLogin(loginId, password);
		}
		int choice;
		do
		{
			System.out.println("Weicome "+controller.getLoginAccount().getName()+" !");
			System.out.println("What can I help you with.");
			System.out.println("1. Add New Order.");
			System.out.println("2. Update Order Details.");
			System.out.println("3. Place Order.");
			System.out.println("4. Logout.");
			System.out.println("Please select an option to proceed:");
			choice = scanner.nextInt();
			while (choice<1 || choice>4)
			{
				System.out.println("Invalid option. Please try again.");
				System.out.println("1. Add New Order.");
				System.out.println("2. Update Order.");
				System.out.println("3. Place Order.");
				System.out.println("4. Logout.");
				System.out.print("Please select an option to proceed: ");
				choice = scanner.nextInt();
			}
			
			switch(choice)
			{
				case 1: addOrder(); 
						break;
				case 2: updateOrder();
						break;
				case 3: placeOrder();
						if(controller.getBillingStatement().checkPaid()==true)
						{
							controller.resetOrder();
							controller.resetLoginAccount();
							return;
						}
						else
							break;
				case 4: {
							controller.resetLoginAccount();
							System.out.println("You have been logout successfully.");
							return;
						}
			}
			
		}while(choice !=4);
		
	}
	
	public void registerCustomerProfile()
	{
		System.out.println("Please fill in the following information.");
		System.out.print("LoginID: ");
		String skip=scanner.nextLine();
		String loginId=scanner.nextLine();
		boolean loginIdExist=controller.searchClientByLoginId(loginId);
		while(loginId=="")
		{
			System.out.println("Login ID requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("LoginID: ");
			loginId=scanner.next();
		}
		while(loginIdExist==true)
		{
			System.out.println("The Login ID is already used by another client. Please enter a new Login ID.");
			System.out.println();
			System.out.print("LoginID: ");
			loginId=scanner.next();
			loginIdExist=controller.searchClientByLoginId(loginId);
		}
		System.out.print("Password: ");
		String password=scanner.nextLine();
		while(password=="")
		{
			System.out.println("Password requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Password: ");
			password=scanner.next();
		}
		System.out.print("Name: ");
		String name=scanner.nextLine();
		while(name=="")
		{
			System.out.println("Name requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Name: ");
			name=scanner.next();
		}
		System.out.print("Mobile No: ");
		String phoneNum=scanner.next();	
		while(phoneNum=="")
		{
			System.out.println("Mobile No requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Mobile No: ");
			phoneNum=scanner.next();
		}
		controller.registerClient(loginId, password, name, phoneNum);
		System.out.println("Client Profile is registered successfully.");
	}
	
	public void loginAdminProfile()
	{
		System.out.print("Login ID: ");
		String loginId=scanner.next();
		System.out.print("Password: ");
		String skip=scanner.nextLine();
		String password=scanner.nextLine();
		boolean login=controller.checkAdminLogin(loginId, password);
		while(login!=true)
		{
			System.out.println("Incorrect loginID or password !");
			System.out.println("Please try again");
			System.out.println();
			System.out.print("Login ID: ");
			loginId=scanner.next();
			System.out.print("Password: ");
			skip=scanner.nextLine();
			password=scanner.nextLine();
			login=controller.checkAdminLogin(loginId, password);
		}
		System.out.println("Weicome "+controller.getLoginAccount().getName()+" !");
		int choice;
		do
		{
			System.out.println("========================================");
			System.out.println("             FOR STAFF ONLY             ");
			System.out.println("========================================");
			System.out.println("1. Add New Order.");
			System.out.println("2. Update Order Details.");
			System.out.println("3. Place Order.");
			System.out.println("4. View Payment Records.");
			System.out.println("5. Edit Menu.");
			System.out.println("6. Register New Admin Account.");
			System.out.println("7. Logout.");
			System.out.println("Please select an option to proceed:");
			choice = scanner.nextInt();
			while (choice<1 || choice>7)
			{
				System.out.println("Invalid option. Please try again.");
				System.out.println();
				System.out.println("========================================");
				System.out.println("             FOR STAFF ONLY             ");
				System.out.println("========================================");
				System.out.println("1. Add New Order.");
				System.out.println("2. Update Order Details.");
				System.out.println("3. Place Order.");
				System.out.println("4. View Payment History.");
				System.out.println("5. Edit Menu.");
				System.out.println("6. Register New Admin Account.");
				System.out.println("7. Logout.");
				System.out.println("Please select an option to proceed:");
				choice = scanner.nextInt();
			}
			
			switch(choice)
			{
				case 1: addOrder(); 
						break;
				case 2: updateOrder();
						break;
				case 3: placeOrder();
						if(controller.getBillingStatement().checkPaid()==true)
							controller.resetOrder();
						break;
				case 4: viewPaymentRecords();
						break;
				case 5: editMenu();
						break;
				case 6: registerAdminProfile();
						break;
				case 7: {
							controller.resetLoginAccount();
							System.out.println("You have been logout successfully.");
							return;
						}
			}
			
		}while(choice !=7);
		
	}
	
	public void addOrder()
	{
		System.out.println("Select the type of Ice Cream that you want.");
		System.out.println("1. Cone");
		System.out.println("2. Sundae");
		int type=scanner.nextInt();
		while(type!=1 && type!=2)
		{
			System.out.println("Invalid type. Please try again.");
			System.out.println("Select the type of Ice Cream that you want.");
			System.out.println("1. Cone");
			System.out.println("2. Sundae");
			type=scanner.nextInt();
		}
		if(type==1)
		{
			displayConeMenu();
			System.out.println("Please select a Cone ice cream by id.");
			String coneId=scanner.next();
			Cone cone=controller.getConeById(coneId);
			Cone c=new Cone(cone.getId(),cone.getNumOfScoops(),cone.getPrice());
			while(c==null)
			{
				System.out.println("Please enter a valid id");
				coneId=scanner.next();
				cone=controller.getConeById(coneId);
				c=new Cone(cone.getId(),cone.getNumOfScoops(),cone.getPrice());
			}
			
			System.out.println();
			displayFlavourMenu();
			System.out.println("Please select "+ c.getNumOfScoops()+" flavour(s) by id for the selected ice cream");
			List<Flavour> fl=new ArrayList <Flavour>();
			for(int i=0;i<c.getNumOfScoops();i++)
			{
				System.out.print("Flavour "+(i+1)+":");
				String flavourId=scanner.next();
				Flavour f=controller.getFlavourById(flavourId);
				while(f==null)
				{
					System.out.println("Please enter a valid id.");
					System.out.print("Flavour "+(i+1)+":");
					flavourId=scanner.next();
					f=controller.getFlavourById(flavourId);
				}
				fl.add(f);
			}
			System.out.println();
			System.out.print("Quantity :");
			int quantity=scanner.nextInt();
			while(quantity<1)
			{
				System.out.println("Please enter a valid quantity.");
				System.out.print("Quantity :");
				quantity=scanner.nextInt();	
			}
			
			System.out.println("Do you want to add toppings?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int addOption=scanner.nextInt();
			while(addOption!=1 &&addOption!=2)
			{
				System.out.println("Please enter a valid option: ");
				addOption=scanner.nextInt();
			}
			controller.addConeToOrder(c, quantity, addOption, fl);
		}
		else if(type==2)
		{
			displaySundaeMenu();
			System.out.println("Please select a Sundae ice cream by id.");
			String sundaeId=scanner.next();
			Sundae sundae;
			sundae=controller.getSundaeById(sundaeId);
			Sundae s=new Sundae(sundae.getId(),sundae.getSize(),sundae.getPrice());
			while(sundae==null)
			{
				System.out.println("Please enter a valid id");
				sundaeId=scanner.next();
				sundae=controller.getSundaeById(sundaeId);
				s=new Sundae(sundae.getId(),sundae.getSize(),sundae.getPrice());
			}
			
			System.out.println();
			displayFlavourMenu();
			System.out.println("Please select the flavour by id for the selected ice cream");
			System.out.print("Flavour: ");
			String flavourId=scanner.next();
			Flavour f=controller.getFlavourById(flavourId);
			while(f==null)
			{
				System.out.println("Please enter a valid id.");
				System.out.print("Flavour: ");
				flavourId=scanner.next();
				f=controller.getFlavourById(flavourId);
			}
			System.out.println();
			System.out.print("Quantity :");
			int quantity=scanner.nextInt();
			while(quantity<1)
			{
				System.out.println("Please enter a valid quantity.");
				System.out.print("Quantity :");
				quantity=scanner.nextInt();	
			}
			
			System.out.println("Do you want to add toppings?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int addOption=scanner.nextInt();
			while(addOption!=1 &&addOption!=2)
			{
				System.out.println("Please enter a valid option: ");
				addOption=scanner.nextInt();
			}	
			controller.addSundaeToOrder(s,quantity,addOption,f);
		}
		System.out.println("Your ice cream is added into cart successfully.");
		System.out.println();
		
	}
	
	public void updateOrder()
	{
		int choice=0;
		do
		{
			if(controller.getOrder().getCart().size()==0)
			{
				System.out.println("Your cart is empty.");
				System.out.println();
				return;
			}
			else
			{
				displayCart();
				System.out.println("Please select an option: ");
				System.out.println("1. Update items.");
				System.out.println("2. Cancel items.");
				System.out.println("3. Back to previous page.");
				choice=scanner.nextInt();
				while(choice<1 || choice>3)
				{
					System.out.println("Invalid option. Please try again.");
					choice=scanner.nextInt();
				}
				if(choice==1)
				{
					System.out.print("Which row of the item inside the cart you want to update: ");
					int updateChoice=scanner.nextInt();
					while(updateChoice<0 || updateChoice>controller.getOrder().getNumberOfTypesOfItemsInsideCart())
					{
						System.out.print("The row selected is not found. Please try again.");
						updateChoice=scanner.nextInt();
					}
					System.out.println("Please enter the quantity you want to update for the selected item.");			
					System.out.print(controller.getOrder().getCart().get(updateChoice-1).getQuantity()+" >>> ");
					int quantity=scanner.nextInt();
					while(quantity<0)
					{
						System.out.println("Please enter a valid quantity.");
						System.out.print(controller.getOrder().getCart().get(updateChoice-1).getQuantity()+" >>> ");
						quantity=scanner.nextInt();	
					}
					controller.updateOrder(updateChoice, quantity);
					System.out.println("The quantity of the item is updated successfully.");
				}
				else if(choice==2)
				{
					System.out.print("Which row of the item inside the cart you want to remove: ");
					int removeChoice=scanner.nextInt();
					System.out.println();
					while(removeChoice<0 || removeChoice>controller.getOrder().getNumberOfTypesOfItemsInsideCart())
					{
						System.out.print("The row selected is not found. Please try again.");
						removeChoice=scanner.nextInt();
					}
					controller.removeOrder(removeChoice);
					System.out.println("The item is removed from cart successfully.");
				}
			}
		}while(choice!=3);
	}
	
	public void placeOrder()
	{
		int choice;
		if(controller.getOrder().getCart().size()==0)
		{
			System.out.println("Your cart is empty.");
			System.out.println();
		}
		else
		{
			displayCart();
			System.out.println("Confirm to place order?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			choice=scanner.nextInt();
			while (choice!=1 & choice!=2)
			{
				System.out.println("Invalid option. Please try again.");
				choice=scanner.nextInt();
			}
			
			switch(choice)
			{
				case 1: {
							controller.placeOrder();
							processPayment();
						}
						break;
				case 2: return;
			}
		}
		
	}
	
	public void displayConeMenu()
	{
		System.out.println("==========================================");
		System.out.println("                   Cone                   ");
		System.out.println("==========================================");
		System.out.println("ID\t"+"Number of Scoops\t"+"Price(RM)");
		for(int i=0;i<controller.getNumberOfCones();i++)
		{
			System.out.println(controller.getConeMenu().get(i).getId()+"\t\t"+controller.getConeMenu().get(i).getNumOfScoops()+"\t\t"+controller.getConeMenu().get(i).getPrice());
		}
		System.out.println("==========================================");
		System.out.println("Add toppings with an additional RM0.50.");
		System.out.println();
	}
	
	public void displaySundaeMenu()
	{
		System.out.println("==========================================");
		System.out.println("                  Sundae                  ");
		System.out.println("==========================================");
		System.out.println("ID\t\t"+"Size\t\t"+"Price(RM)");
		for(int i=0;i<controller.getNumberOfSundaes();i++)
		{
			System.out.println(controller.getSundaeMenu().get(i).getId()+"\t\t"+controller.getSundaeMenu().get(i).getSize()+"\t\t"+controller.getSundaeMenu().get(i).getPrice());
		}
		System.out.println("==========================================");
		System.out.println("Add toppings with an additional RM0.50.");
		System.out.println();
	}
	
	public void displayFlavourMenu()
	{
		System.out.println("======================");
		System.out.println("        Flavour       ");
		System.out.println("======================");
		System.out.println("ID\t"+"Flavour");
		for(int i=0;i<controller.getNumberOfFlavours();i++)
		{
			System.out.println(controller.getFlavourMenu().get(i).getId()+"\t"+controller.getFlavourMenu().get(i).getName());
		}
		System.out.println("======================");
		System.out.println();
	}
	
	public void displayCart()
	{
		System.out.println("=======================================================================================================================");
		System.out.println("                                                       Your Cart                                                       ");
		System.out.println("=======================================================================================================================");
		System.out.println("No.\t"+"ID\t"+"Type\t\t\t"+"Details\t\t\t\t\t\t"+"Quantity\t"+"Price/unit(RM)\t");
		
		for(int i=0;i<controller.getOrder().getNumberOfTypesOfItemsInsideCart();i++)
		{	
			
			String type=controller.getOrder().getIceCreamTypes()[i];
			List<IceCream> cart=controller.getOrder().getCart();
			String addOnDetails;
			if(cart.get(i).checkAddOn()==true)
				addOnDetails="Add Toppings";
			else
				addOnDetails="No Toppings";
			if(type=="Cone")
			{
				String flavourDetails="";
				for(int j=0;j<((Cone)cart.get(i)).getFlavours().size();j++) 
				{
					flavourDetails += ((Cone)cart.get(i)).getFlavours().get(j).getName()+", ";
				}
				System.out.println((i+1)+"."+"\t"+cart.get(i).getId()+"\t"+type+"\t"+((Cone)cart.get(i)).getNumOfScoops()+" Scoops"+" ("+flavourDetails+addOnDetails+")\t\t"+cart.get(i).getQuantity()+"\t\t"+cart.get(i).getPrice());	
			}
			else if(type=="Sundae")
			{
				System.out.println((i+1)+"."+"\t"+cart.get(i).getId()+"\t"+type+"\t"+((Sundae)cart.get(i)).getSize()+" ("+((Sundae)cart.get(i)).getFlavour().getName()+" "+addOnDetails+")\t\t"+cart.get(i).getQuantity()+"\t\t"+cart.get(i).getPrice());	
			}
		}
		System.out.println("=======================================================================================================================");
		System.out.println("\t\t\t\t\t\t\t\t\t"+"Total Quantity: "+controller.getOrder().getNumberOfItemsInsideCart()+"\tTotal Price: RM"+controller.getOrder().getTotalPrice());
		System.out.println("=======================================================================================================================");
		System.out.println();
	}
	
	public void processPayment()
	{
		int option=0;
		double amount=0;
		do
		{
			option=0;
			displayBillingStatement();
			System.out.println("Please select a pay method.");
			System.out.println("1. Touch n Go eWallet.");
			System.out.println("2. Debit or Credit Card.");
			System.out.println("3. Cash.");
			int choice=scanner.nextInt();
			while(choice <1 || choice>3)
			{
				System.out.println("Invalid pay method. Please try again.");
				choice=scanner.nextInt();
			}
			String method=null;
			if(choice==1)
				method="Touch n Go eWallet";
			else if(choice==2)
				method="Debit or Credit Card";
			else if(choice==3)
				method="Cash";
			System.out.println("Please pay for RM"+controller.getBillingStatement().getTotalPrice());
			System.out.print("Enter your amount: RM");
			amount=scanner.nextDouble();
			controller.getBillingStatement().processPayment(method, amount);
			
			while(controller.getBillingStatement().checkPaid()==false && option!=2)
			{
				System.out.println("Insufficient amount. Please try again.");
				System.out.println("Select an option:");
				System.out.println("1. Continue with current payment method.");
				System.out.println("2. Try with another payment method");
				System.out.println("3. Cancel Payment.");
				option=scanner.nextInt();
				while(option<1 || option>3)
				{
					System.out.println("Invalid option. Please try again.");
					option=scanner.nextInt();
				}
				if(option==3)
					return;
				else if(option==1)
				{
					System.out.print("Enter your amount: RM");
					amount=scanner.nextDouble();
					controller.getBillingStatement().processPayment(method, amount);
					if(controller.getBillingStatement().checkPaid()==true)
					{
						option=0;
					}
				}
			}
		}while(option==2);
		controller.addPaymentRecord();
		System.out.println("You have paid RM"+amount+" to Cool Fresh Ice Cream Store Successfully.");
		System.out.println("We are processing your order.");
		System.out.println("Please be patient.");
		System.out.println("Thank you! Wish you have a nice day. ^_^");
		System.out.println();
		displayReceipt();
	}
	
	public void displayBillingStatement()
	{
		System.out.println("====================================");
		System.out.println("          Billing Statement          ");
		System.out.println("====================================");
		System.out.println("Bill Reference: "+controller.getBillingStatement().getBillRef());
		if((controller.getBillingStatement().getOrder().getUser() instanceof Client))
			System.out.println("Client ID: "+((Client)controller.getBillingStatement().getOrder().getUser()).getClientId());
		else if((controller.getBillingStatement().getOrder().getUser() instanceof Administrator))
			System.out.println("Admin ID: "+((Administrator)controller.getBillingStatement().getOrder().getUser()).getAdminId());
		System.out.println("Order ID: "+controller.getBillingStatement().getOrder().getOrderId());
		System.out.println("Order Date: "+controller.getBillingStatement().getOrder().getOrderDate());
		System.out.println("Order Time: "+controller.getBillingStatement().getOrder().getOrderTime());
		System.out.println("Total Price To Be Paid: RM"+controller.getBillingStatement().getTotalPrice());
		System.out.println("====================================");
		System.out.println();
	}
	
	public void displayReceipt()
	{
		System.out.println("=====================================");
		System.out.println("                Receipt              ");
		System.out.println("=====================================");
		System.out.println("Bill Reference: "+controller.getBillingStatement().getBillRef());
		if((controller.getBillingStatement().getOrder().getUser() instanceof Client))
			System.out.println("Client ID: "+((Client)controller.getBillingStatement().getOrder().getUser()).getClientId());
		else if((controller.getBillingStatement().getOrder().getUser() instanceof Administrator))
			System.out.println("Admin ID: "+((Administrator)controller.getBillingStatement().getOrder().getUser()).getAdminId());
		System.out.println("Order ID: "+controller.getBillingStatement().getOrder().getOrderId());
		System.out.println("Status: Paid by " +controller.getBillingStatement().getMethod());
		System.out.println("Bill Date: "+controller.getBillingStatement().getBillDate());
		System.out.println("Bill Time: "+controller.getBillingStatement().getBillTime());
		System.out.println("=====================================");
		System.out.println("Total Price: RM"+controller.getBillingStatement().getTotalPrice());
		System.out.println("Paid Amount: RM"+controller.getBillingStatement().getPaidAmount());
		System.out.println("Change: RM"+controller.getBillingStatement().getReturnAmount());
		System.out.println("=====================================");
		System.out.println();
	}
	
	public void viewPaymentRecords()
	{
		int choice;
		do
		{
			System.out.println("Please select an option :");
			System.out.println("1. View All Payment Records.");
			System.out.println("2. Search Payment Records By BillDate.");
			System.out.println("3. Search Payment Records By UserId.");
			System.out.println("4. Back to Previous Page.");
			choice=scanner.nextInt();
			while(choice<1 || choice>4)
			{
				System.out.println("Invalid option. Please try again.");
				System.out.println("Please select an option :");
				System.out.println("1. View All Payment Records.");
				System.out.println("2. Search Payment Records By BillDate.");
				System.out.println("3. Search Payment Records By UserId.");
				System.out.println("4. Back to Previous Page.");
				choice=scanner.nextInt();
			}
			switch(choice)
			{
				case 1: viewAllPaymentRecords();
						break;
				case 2: viewPaymentRecordsByBillDate();
						break;
				case 3: viewPaymentRecordsByUserId();
						break;
				case 4: return;
			}
		}while(choice!=4);
		
	}
	
	public void viewAllPaymentRecords()
	{
		List<BillingStatement> paymentRecords=controller.getPaymentRecords();
		if(paymentRecords.size()==0)
		{
			System.out.println("No payment record is found.");
			System.out.println();
		}
		else
		{
			System.out.println("==========================================================================================================");
			System.out.println("                                                Payment Records                                           ");
			System.out.println("==========================================================================================================");
			System.out.println("No.\tBill Ref\tBill Date\tBill Time\tUser ID\t\tPay Method\tTotal Amount(RM)");
			for(int i=0;i<paymentRecords.size();i++)
			{
				BillingStatement record=paymentRecords.get(i);
				System.out.println((i+1)+".\t"+record.getBillRef()+"\t\t"+record.getBillDate()+"\t"+record.getBillTime()+"\t"+record.getUserId()+"\t"+record.getMethod()+"\t"+record.getTotalPrice());
			}
			System.out.println("==========================================================================================================");
			System.out.println("\t\t\t\t\t\t\t\t\t\tTotal Revenue: RM"+controller.getTotalRevenue(paymentRecords));
			System.out.println("==========================================================================================================");
			System.out.println();
		}
	}
	
	public void viewPaymentRecordsByBillDate()
	{
		System.out.print("Please enter the bill date that you want to search (Ex: yyyy-MM-dd, 2023-04-28): ");
		String billDate=scanner.next();
		System.out.println();
		List<BillingStatement> paymentRecords=controller.getPaymentRecordsByBillDate(billDate);
		if(paymentRecords.size()==0)
		{
			System.out.println("No payment record is found on "+billDate+".");
			System.out.println();
		}
		else
		{
			System.out.println("==========================================================================================================");
			System.out.println("                                    Payment Records (Bill Date: "+billDate+")");                                        
			System.out.println("==========================================================================================================");
			System.out.println("No.\tBill Ref\tBill Date\tBill Time\tUser ID\t\tPay Method\tTotal Amount(RM)");
			for(int i=0;i<paymentRecords.size();i++)
			{
				BillingStatement record=paymentRecords.get(i);
				System.out.println((i+1)+".\t"+record.getBillRef()+"\t\t"+record.getBillDate()+"\t"+record.getBillTime()+"\t"+record.getUserId()+"\t"+record.getMethod()+"\t"+record.getTotalPrice());
			}
			System.out.println("==========================================================================================================");
			System.out.println("\t\t\t\t\t\t\t\t\t\tTotal Revenue: RM"+controller.getTotalRevenue(paymentRecords));
			System.out.println("==========================================================================================================");
			System.out.println();
		}
	}
	
	public void viewPaymentRecordsByUserId()
	{
		System.out.print("Please enter the User ID that you want to search (Ex: CL00001): ");
		String id=scanner.next();
		System.out.println();
		List<BillingStatement> paymentRecords=controller.getPaymentRecordsByUserId(id);
		if(paymentRecords.size()==0)
		{
			System.out.println("No payment record is found for "+id+".");
			System.out.println();
		}
		else
		{
			System.out.println("==========================================================================================================");
			System.out.println("                                      Payment Records (User ID: "+id+")");                                        
			System.out.println("==========================================================================================================");
			System.out.println("No.\tBill Ref\tBill Date\tBill Time\tUser ID\t\tPay Method\tTotal Amount(RM)");
			for(int i=0;i<paymentRecords.size();i++)
			{
				BillingStatement record=paymentRecords.get(i);
				System.out.println((i+1)+".\t"+record.getBillRef()+"\t\t"+record.getBillDate()+"\t"+record.getBillTime()+"\t"+record.getUserId()+"\t"+record.getMethod()+"\t"+record.getTotalPrice());
			}
			System.out.println("==========================================================================================================");
			System.out.println("\t\t\t\t\t\t\t\t\t\tTotal Revenue: RM"+controller.getTotalRevenue(paymentRecords));
			System.out.println("==========================================================================================================");
			System.out.println();
		}
	}
	
	public void editMenu()
	{
		int choice;
		do
		{
			displayConeMenu();
			displaySundaeMenu();
			displayFlavourMenu();
			System.out.println("Which menu to edit.");
			System.out.println("1. Cone Menu.");
			System.out.println("2. Sundae Menu.");
			System.out.println("3. Flavour Menu");
			System.out.println("4. Back to Previous Page.");
			choice=scanner.nextInt();
			while(choice<1 || choice>4)
			{
				System.out.println("Invalid option. Please try again. ");
				choice=scanner.nextInt();
			}
			
			switch(choice)
			{
				case 1: editConeMenu();
						break;
				case 2: editSundaeMenu();
						break;
				case 3: editFlavourMenu();
						break;
				case 4: return;
			}
		}while(choice!=4);
	}
	
	public void editConeMenu()
	{
		System.out.println("Please select an option:");
		System.out.println("1. Add Cone.");
		System.out.println("2. Edit Cone.");
		System.out.println("3. Remove Cone.");
		System.out.println("4. Back to Previous Page.");
		int choice=scanner.nextInt();
		while(choice<1 || choice>4)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		
		switch(choice)
		{
			case 1: addCone();
					break;
			case 2: editCone();
					break;
			case 3: removeCone();
					break;
			case 4: return;
		}
	}
	
	public void addCone()
	{
		System.out.print("Number of scoops: ");
		int numOfScoops=scanner.nextInt();
		boolean numOfScoopsExist=controller.checkConeByNumOfScoops(numOfScoops);
		while(numOfScoops<0 || numOfScoopsExist==true)
		{
			System.out.print("Please enter a valid number: ");
			numOfScoops=scanner.nextInt();
			numOfScoopsExist=controller.checkConeByNumOfScoops(numOfScoops);
		}
		System.out.print("Price: ");
		double price=scanner.nextDouble();
		while(price<0)
		{
			System.out.print("Please enter a valid price amount: ");
			price=scanner.nextDouble();
		}
		controller.addConeToMenu(numOfScoops, price);
		System.out.println("The cone is added successfully.");
		System.out.println();
	}
	
	public void editCone()
	{
		System.out.println("Please enter the ID of the Cone that you want to edit.");
		System.out.print("Id: ");
		String id=scanner.next();
		boolean idExist=controller.checkConeById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkConeById(id);
		}
		System.out.print("Number of scoops to update: ");
		int numOfScoops=scanner.nextInt();
		while(numOfScoops<0)
		{
			System.out.print("Please enter a valid number: ");
			numOfScoops=scanner.nextInt();
		}
		System.out.print("Price to update: ");
		double price=scanner.nextDouble();
		while(price<0)
		{
			System.out.print("Please enter a valid price amount: ");
			price=scanner.nextDouble();
		}
		controller.editConeInMenu(numOfScoops, price,id);
		System.out.println("The cone is edited successfully.");
		System.out.println();
	}
	
	public void removeCone()
	{
		System.out.println("Please enter the ID of the cone to remove.");
		System.out.print("ID: ");
		String id=scanner.next();
		boolean idExist=controller.checkConeById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkConeById(id);
		}
		Cone c=controller.getConeById(id);
		System.out.println("================================================================");
		System.out.println("ID: "+c.getId()+"\t\tNumber Of Scoops: "+c.getNumOfScoops()+"\tPrice/unit: "+c.getPrice());
		System.out.println("================================================================");
		System.out.println("Are you sure to remove this cone from menu?");
		System.out.println("1.Yes.");
		System.out.println("2.No.");
		int choice=scanner.nextInt();
		while(choice!=1 && choice!=2)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		System.out.println();
		if(choice==1)
		{
			controller.deleteConeFromMenu(id);
			System.out.println("The cone is removed successfully.");
			System.out.println();
		}
	}
	
	public void editSundaeMenu()
	{
		System.out.println("Please select an option:");
		System.out.println("1. Add Sundae.");
		System.out.println("2. Edit Sundae.");
		System.out.println("3. Remove Sundae.");
		System.out.println("4. Back to Previous Page.");
		int choice=scanner.nextInt();
		while(choice<1 || choice>4)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		
		switch(choice)
		{
			case 1: addSundae();
					break;
			case 2: editSundae();
					break;
			case 3: removeSundae();
					break;
			case 4: return;
		}
	}
	
	public void addSundae()
	{
		System.out.print("Size: ");
		String size=scanner.next();
		boolean sizeExist=controller.checkSundaeBySize(size);
		while(sizeExist==true)
		{
			System.out.print("Please enter a valid number: ");
			size=scanner.next();
			sizeExist=controller.checkSundaeBySize(size);
		}
		System.out.print("Price: ");
		double price=scanner.nextDouble();
		while(price<0)
		{
			System.out.print("Please enter a valid price amount: ");
			price=scanner.nextDouble();
		}
		controller.addSundaeToMenu(size, price);
		System.out.println("The sundae is added successfully.");
		System.out.println();
	}
	
	public void editSundae()
	{
		System.out.println("Please enter the ID of the Sundae that you want to edit.");
		System.out.print("Id: ");
		String id=scanner.next();
		boolean idExist=controller.checkSundaeById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkSundaeById(id);
		}
		System.out.print("Size to update: ");
		String size=scanner.next();
		System.out.print("Price to update: ");
		double price=scanner.nextDouble();
		while(price<0)
		{
			System.out.print("Please enter a valid price amount: ");
			price=scanner.nextDouble();
		}
		controller.editSundaeInMenu(size, price,id);
		System.out.println("The Sundae is edited successfully.");
		System.out.println();
	}
	
	public void removeSundae()
	{
		System.out.println("Please enter the ID of the sundae to remove.");
		System.out.print("ID: ");
		String id=scanner.next();
		boolean idExist=controller.checkSundaeById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkSundaeById(id);
		}
		Sundae s=controller.getSundaeById(id);
		System.out.println("================================================================");
		System.out.println("ID: "+s.getId()+"\t\tSize: "+s.getSize()+"\tPrice/unit: "+s.getPrice());
		System.out.println("================================================================");
		System.out.println("Are you sure to remove this sundae from menu?");
		System.out.println("1.Yes.");
		System.out.println("2.No.");
		int choice=scanner.nextInt();
		while(choice!=1 && choice!=2)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		System.out.println();
		if(choice==1)
		{
			controller.deleteSundaeFromMenu(id);
			System.out.println("The sundae is removed successfully.");
			System.out.println();
		}
	}
	
	public void editFlavourMenu()
	{
		System.out.println("Please select an option:");
		System.out.println("1. Add Flavour.");
		System.out.println("2. Edit Flavour.");
		System.out.println("3. Remove Flavour.");
		System.out.println("4. Back to Previous Page.");
		int choice=scanner.nextInt();
		while(choice<1 || choice>4)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		
		switch(choice)
		{
			case 1: addFlavour();
					break;
			case 2: editFlavour();
					break;
			case 3: removeFlavour();
					break;
			case 4: return;
		}
	}
	
	public void addFlavour()
	{
		System.out.print("Name: ");
		String name=scanner.next();
		boolean flavourExist=controller.checkFlavourByName(name);
		while(flavourExist==true)
		{
			System.out.print("Please enter a valid number: ");
			name=scanner.next();
			flavourExist=controller.checkFlavourByName(name);
		}
		controller.addFlavourToMenu(name);
		System.out.println("The flavour is added successfully.");
		System.out.println();
	}
	
	public void editFlavour()
	{
		System.out.println("Please enter the ID of the Flavour that you want to edit.");
		System.out.print("Id: ");
		String id=scanner.next();
		boolean idExist=controller.checkFlavourById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkFlavourById(id);
		}
		System.out.print("Flavour Name to update: ");
		String name=scanner.next();
		controller.editFlavourInMenu(name, id);
		System.out.println("The flavour is edited successfully.");
		System.out.println();
	}
	
	public void removeFlavour()
	{
		System.out.println("Please enter the ID of the flavour to remove.");
		System.out.print("ID: ");
		String id=scanner.next();
		boolean idExist=controller.checkFlavourById(id);
		while(idExist==false)
		{
			System.out.println("The ID you entered does not exist. Please try again.");
			System.out.print("ID: ");
			id=scanner.next();
			idExist=controller.checkFlavourById(id);
		}
		Flavour f=controller.getFlavourById(id);
		System.out.println("=======================================");
		System.out.println("ID: "+f.getId()+"\t\tName: "+f.getName());
		System.out.println("=======================================");
		System.out.println("Are you sure to remove this flavour from menu?");
		System.out.println("1.Yes.");
		System.out.println("2.No.");
		int choice=scanner.nextInt();
		while(choice!=1 && choice!=2)
		{
			System.out.println("Invalid option. Please try again.");
			choice=scanner.nextInt();
		}
		System.out.println();
		if(choice==1)
		{
			controller.deleteFlavourFromMenu(id);
			System.out.println("The flavour is removed successfully.");
			System.out.println();
		}
	}
	
	public void registerAdminProfile()
	{
		System.out.println("Please fill in the following information.");
		System.out.print("LoginID: ");
		String skip=scanner.nextLine();
		String loginId=scanner.nextLine();
		boolean loginIdExist=controller.searchAdminByLoginId(loginId);
		while(loginId=="")
		{
			System.out.println("Login ID requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("LoginID: ");
			loginId=scanner.next();
		}
		while(loginIdExist==true)
		{
			System.out.println("The Login ID is already used by another admin. Please enter a new Login ID.");
			System.out.println();
			System.out.print("LoginID: ");
			loginId=scanner.next();
			loginIdExist=controller.searchAdminByLoginId(loginId);
		}
		System.out.print("Password: ");
		String password=scanner.nextLine();
		while(password=="")
		{
			System.out.println("Password requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Password: ");
			password=scanner.next();
		}
		System.out.print("Name: ");
		String name=scanner.nextLine();
		while(name=="")
		{
			System.out.println("Name requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Name: ");
			name=scanner.next();
		}
		System.out.print("Mobile No: ");
		String phoneNum=scanner.next();	
		while(phoneNum=="")
		{
			System.out.println("Mobile No requirement is empty. Please fill in again");
			System.out.println();
			System.out.print("Mobile No: ");
			phoneNum=scanner.next();
		}
		controller.registerAdmin(loginId, password, name, phoneNum);
		System.out.println("Admin Profile is registered successfully.");
	}
	
}
