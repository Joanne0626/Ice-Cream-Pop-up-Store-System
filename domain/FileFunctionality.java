package billing.domain;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FileFunctionality {
	
	private File myFile;
	private Scanner fileReader;
	
	public FileFunctionality(String filename)
	{
		this.setFile(filename);
	}
	
	public void setFile(String fileName)
	{
		try {
			myFile=new File(fileName);
			fileReader=new Scanner(myFile);
		}catch (FileNotFoundException e) {
			System.out.print("File not found");
		}

	}
	
	public List<Client> readClientFile()
	{
		List<Client> cl=new ArrayList<Client>();
		while(fileReader.hasNextLine())
		{
			String clientId=fileReader.nextLine();
			String loginId=fileReader.nextLine();
			String password=fileReader.nextLine();
			String name=fileReader.nextLine();
			String phoneNum=fileReader.nextLine();
			Client c=new Client(loginId,password,name,phoneNum,clientId);
			cl.add(c);
		}
		return cl;
	}
	
	public List<Administrator> readAdminFile()
	{
		List<Administrator> al=new ArrayList<Administrator>();
		while(fileReader.hasNextLine())
		{
			String adminId=fileReader.nextLine();
			String loginId=fileReader.nextLine();
			String password=fileReader.nextLine();
			String name=fileReader.nextLine();
			String phoneNum=fileReader.nextLine();
			Administrator a=new Administrator(loginId,password,name,phoneNum,adminId);
			al.add(a);
		}
		return al;
	}
	
	public List<Cone> readConeFile()
	{
		List<Cone> cl=new ArrayList<Cone>();
		while(fileReader.hasNextLine())
		{
			String id=fileReader.next();
			int numOfScoops=fileReader.nextInt();
			double price=fileReader.nextDouble();
			Cone c=new Cone(id,numOfScoops,price);
			cl.add(c);
		}
		return cl;
	}
	
	public List<Sundae> readSundaeFile()
	{
		List<Sundae> sl=new ArrayList<Sundae>();
		while(fileReader.hasNextLine())
		{
			String id=fileReader.next();
			String size=fileReader.next();
			double price=fileReader.nextDouble();
			Sundae s=new Sundae(id,size,price);
			sl.add(s);
		}
		return sl;
	}
	
	public List<Flavour> readFlavourFile()
	{
		List<Flavour> fl=new ArrayList<Flavour>();
		while(fileReader.hasNextLine())
		{
			String id=fileReader.nextLine();
			String name=fileReader.nextLine();
			Flavour f=new Flavour(id,name);
			fl.add(f);
		}
		return fl;
	}
	
	public List<BillingStatement> readPaymentRecordsFile()
	{
		DateTimeFormatter date=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter time=DateTimeFormatter.ofPattern("HH:mm:ss");
		List<BillingStatement>pl=new ArrayList<BillingStatement>();
		while(fileReader.hasNextLine())
		{

			String billRef=fileReader.nextLine();
			String bd=fileReader.nextLine();
			String bt=fileReader.nextLine();
			String userId=fileReader.nextLine();
			String method=fileReader.nextLine();
			double totalPrice=fileReader.nextDouble();
			LocalDate billDate=LocalDate.parse(bd,date);
			LocalTime billTime=LocalTime.parse(bt,time);
			BillingStatement p=new BillingStatement(billRef,billDate,billTime,userId,method,totalPrice);
			pl.add(p);
		}
		return pl;
	}

}
