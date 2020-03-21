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
		Database db = new Database();
	}
	
	public Database getDb() {
		return db;
	}

	public void setDb(Database db) {
		this.db = db;
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
		return db.searchClientID(id);
	}
	
	public Client findClient(String email) {
		return db.searchEmail(email);
	}

	
	
}
