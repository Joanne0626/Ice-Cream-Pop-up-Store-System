package billing.domain;

import java.util.List;

public class ClientList implements IClientList {

	private List<Client> clientList;
	
	public ClientList()
	{
		this.setClientList();
	}
	
	public List<Client> getClientList()
	{
		return clientList;
	}
	
	public void setClientList()
	{
		FileFunctionality clientFile=new FileFunctionality("clients.txt");
		clientList=clientFile.readClientFile();
	}
	
	public int getNumOfClients()
	{
		return clientList.size();
	}
	
	public void addClient(Client c)
	{
			clientList.add(c);
	}
	
	public boolean searchClientByLoginId(String id,Client c)
	{
		if(id.equals(c.getLoginId()))
			return true;
		else
			return false;
	}
}
