package billing.domain;

import java.util.List;

public interface IFlavourList {

	public List<Flavour> getFlavourList();
	public void setFlavourList();
	public int getNumOfFlavours();
	public boolean checkFlavourById(String id,Flavour f);
	public boolean checkFlavourByName(String name,Flavour f);
	public void addFlavour(Flavour f);
	public void editFlavour(int i,Flavour f);
	public void deleteFlavour(String id);
}
