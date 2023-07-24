package billing.domain;

import java.util.List;

public interface IAdminList {

	public List <Administrator> getAdminList();
	public void setAdminList();
	public int getNumOfAdmins();
	public void addAdmin(Administrator a);
	public boolean searchAdminByLoginId(String id,Administrator a);
}
