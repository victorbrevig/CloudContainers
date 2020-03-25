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
		this.db = new Database();
	}
	
	public Database getDb() {
		return db;
	}
	public void addClient(Client client) {
		db.addClient(client);
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
	
	public void removeClient(int clientID) {
		int id = this.db.searchClientIDIndex(clientID);
		if (id == -1) {
			System.out.println("Not an existing client");
		}
		this.db.removeClient(id);
	}
	public void removeClient(String email) {
		int id = this.db.searchEmailIndex(email);
		if (id == -1) {
			System.out.println("Not an existing client");
		}else {
		this.db.removeClient(id);}
	}
	
	
	public boolean exist(Client client) {
		return this.db.exist(client);
	}

}
