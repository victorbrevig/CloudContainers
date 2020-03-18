package CloudContainers;

public class Client {
	
	private String name;
	private String clientID;
	private String email;
	private String age;
	private String gender;
	private String number;
	
	public Client(String name, String clientID, String email, String age, String gender, String number) {
		super();
		this.name = name;
		this.clientID = clientID;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClientID() {
		return clientID;
	}
	//Add a random generator for clienID
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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

	

}


