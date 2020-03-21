package CloudContainers;

import java.util.ArrayList;

public class LogisticCompany {
	private String name;
	private String companyID;
	private Database db;
	private ArrayList<Container> containers;
	
	public LogisticCompany(String name, String companyID) {
		super();
		this.name = name;
		this.companyID = companyID;
		db = new Database();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyID() {
		return companyID;
	}

	public Client findClient(int id) {
		
	}
	
	
	
	
	
	
}
