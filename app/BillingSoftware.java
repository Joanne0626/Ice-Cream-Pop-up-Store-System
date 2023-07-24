package billing.app;

import billing.domain.*;

public class BillingSoftware {
	
	public static void main(String[] args) 
	{
		Controller controller=new Controller();
		ConsoleUI userInterface = new ConsoleUI(controller);
		userInterface.start();
	}
}
