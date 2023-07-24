package billing.domain;

import java.util.List;

public interface IClientList {

	public List<Client> getClientList();
	public void setClientList();
	public int getNumOfClients();
	public void addClient(Client c);
	public boolean searchClientByLoginId(String id,Client c);
}
