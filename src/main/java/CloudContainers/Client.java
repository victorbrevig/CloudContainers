package CloudContainers;
// New version
public class Client {
	private String name1;
	private int clientID;
	private String email;
	// Change to birthday
	private String birthdate;
	private String gender;
	private String number;
	
	public Client(String name, int clientID, String email, String birthdate, String gender, String number) {
		super();
		this.name1 = name;
		this.clientID = clientID;
		this.email = email;
		this.birthdate = birthdate;
		this.gender = gender;
		this.number = number;
	}
	
	
	public String getName() {
		return name1;
	}
	public void setName(String name) {
		this.name1 = name;
	}
	public int getClientID() {
		return clientID;
	}
	//Add a random generator for clienID
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public void print() {
		System.out.println("Name:" + this.getName());
		System.out.println("Client ID:" + this.getClientID());
		System.out.println("Email:" + this.getEmail());
		System.out.println("Birthdate:" + this.getBirthdate());
		System.out.println("Gender:" + this.getGender());
		System.out.println("Phone number:" + this.getNumber());
		
	}

	

}


