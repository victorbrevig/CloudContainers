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
	static String filepath = "lc.json";
	static String filepathin = "in.json";
 
 public static LogisticCompany getCompany() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepath);
	 LogisticCompany company = gson.fromJson(reader, LogisticCompany.class);
	 return company;
	 
 }
 
 public static void saveCompany(LogisticCompany company) throws IOException {
	 Gson gson = new Gson();
	 Writer fw = new FileWriter("lc.json");
	 gson.toJson(company, fw);
     fw.flush();
     fw.close();
 }
 
 public static Client getIn() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepathin);
	 Client client = gson.fromJson(reader, Client.class);
	 return client;
 }
 
 public static void setIn(Client client) throws IOException {
	 Gson gson = new Gson();
	 Writer fwin = new FileWriter("in.json");
	 gson.toJson(client, fwin);
     fwin.flush();
     fwin.close();
 }
 
 public static boolean checkPass(String mail, String pass) throws IOException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepath);
	 LogisticCompany company = gson.fromJson(reader, LogisticCompany.class);
	 ClientDatabase ClientDB = company.getClients();
	 Client client = ClientDB.getClient(mail);
	 if ((client.getPassword()).equals(pass)) {
		 return true;
	 }
	 return false;
 }
 

public static void main(String[] args) throws IOException {
	LogisticCompany company = JSONWriter.getCompany();
	ClientDatabase ClientDB = company.getClients();
	Client client = ClientDB.getClient("mediumman1@dtu.dk");
	Client client1 = new Client("Bob1","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	Client client2 = new Client("Bob2","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	Client client3 = new Client("Bob3","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	company.newClient(client1);
	company.newClient(client2);
	company.newClient(client3);
	JSONWriter.saveCompany(company);
	System.out.println(JSONWriter.checkPass("mediumman1@dtu.dk", "kode123"));
	if (JSONWriter.checkPass("mediumman1@dtu.dk", "kode123")) {
		System.out.println("Virker");
	}
	JSONWriter.setIn(client);
	System.out.println("Halal");
}
}
