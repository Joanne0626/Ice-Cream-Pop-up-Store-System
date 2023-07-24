package billing.domain;

import java.util.List;

public interface ISundaeList {

	public List<Sundae> getSundaeList();
	public void setSundaeList();
	public int getNumOfSundaes();
	public boolean checkSundaeById(String id,Sundae s);
	public boolean checkSundaeBySize(String size,Sundae s);
	public void addSundae(Sundae s);
	public void editSundae(int i,Sundae s);
	public void deleteSundae(String id);
}
