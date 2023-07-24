package billing.domain;

import java.util.List;

public interface IConeList {

	public List<Cone> getConeList();
	public void setConeList();
	public int getNumOfCones();
	public boolean checkConeById(String id,Cone c);
	public boolean checkConeByNumOfScoops(int numOfScoops,Cone c);
	public void addCone(Cone c);
	public void editCone(int i,Cone c);
	public void deleteCone(String id);
	
}
