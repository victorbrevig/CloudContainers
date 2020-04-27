package Cloud.model;

//Java program for write JSON to a file 

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.LinkedHashMap; 
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import Cloud.model.Client;
import Cloud.model.ClientDatabase; 

public class JSONWriter{ 

	// Victor path
	String locClients = "C:\\Users\\victo\\git\\CloudContainers\\Y.json";
	String locLoggedin = "C:\\Users\\victo\\git\\CloudContainers\\Loggedin.json";
	
	
	
 public JSONWriter() throws IOException {
//	 Gson gson = new Gson();
//	 Writer fww = new FileWriter("Y.json");
//	 HashSet <Client> database2 = new HashSet <Client>();
//	 gson.toJson(database2, fww);
//     fww.flush();
//     fww.close();
     Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 Writer fw = new FileWriter(locClients);
	 HashSet<Client> database = db;
     gson.toJson(database, fw);
     fw.flush();
     fw.close();
 }
 public void addClient(Client client) throws IOException{
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 Writer fw = new FileWriter(locClients);
	 HashSet<Client> database = db ;
	 database.add(client);
     gson.toJson(database, fw);
     fw.flush();
     fw.close();
 }
 public void addClient2(Client client2) throws IOException{
	 Gson gson = new Gson();
	 Writer fw = new FileWriter(locLoggedin);
	 HashSet <Client> database = new HashSet<Client>();
	 database.add(client2);
     gson.toJson(database, fw);
     fw.flush();
     fw.close();
 }
 public boolean checkPass(String user, String pass) throws IOException{
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 for (Client client : db) {
	        if (client.getEmail().equals(user)) {
	        	if (client.getPassword().equals(pass)) {
	        		return true;
	        	}
	        }	  
	 }
	return false; 
 }
 public ClientDatabase dbReturner() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 return db;
	 
 }
 public ClientDatabase pop(Client client) throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 db.remove(client);
	 return db;
	 
 }
// public static void main(String[] args) throws IOException {
//	 JSONWriter wr = new JSONWriter();
//	 Client client = new Client("Bob1",001,"mediumman1@dtu.dk","22","male","10101010");
//	 Client client2 = new Client("Marley",002,"bigman2@dtu.dk","22","male","10101010");
//	 wr.addClient(client);
//	 wr.addClient(client2);
//	 wr.test();
//}
public void Remove(String email) throws IOException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locClients);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 Writer fw = new FileWriter(locClients);
	 
	 System.out.println(db.remove(db.getClient(email)));
	 HashSet<Client> database = db ;
    gson.toJson(database, fw);
    fw.flush();
    fw.close();
	// TODO Auto-generated method stub
	
}
public Client getClient() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(locLoggedin);
	 ClientDatabase db = gson.fromJson(reader, ClientDatabase.class);
	 
	 HashSet<Client> database = db ;
	 Client client = database.stream().findFirst().get();
	 return client;
 }
	
	

public static void main(String[] args) throws IOException {
	JSONWriter wr = new JSONWriter();
	Client client = new Client("mo","JegErMO","12","male",28381812,"12");
	//wr.getClient().print();
	
	
	
}
}
