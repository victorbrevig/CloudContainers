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
	// Mo Path
	String filepath = "lc.json";
	
 public JSONWriter() throws IOException {
	 Gson gson = new Gson();
	 Writer fw = new FileWriter("lc.json");
	 LogisticCompany company = new LogisticCompany("Maersk",1,2,"bigstonks");
	 gson.toJson(company, fw);
     fw.flush();
     fw.close();
 }
 
 public LogisticCompany getCompany() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepath);
	 LogisticCompany company = gson.fromJson(reader, LogisticCompany.class);
	 return company;
	 
 }
 
 public void saveCompany(LogisticCompany company) throws IOException {
	 Gson gson = new Gson();
	 Writer fw = new FileWriter("lc.json");
	 gson.toJson(company, fw);
     fw.flush();
     fw.close();
 }

public static void main(String[] args) throws IOException {
	JSONWriter jw = new JSONWriter();
	LogisticCompany company = jw.getCompany();
	Client client = new Client("Bob1","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	Client client2 = new Client("Bob2","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	Client client3 = new Client("Bob3","mediumman1@dtu.dk","22-12-1900","male",10101010,"kode123");
	company.newClient(client);
	company.newClient(client2);
	company.newClient(client3);
	jw.saveCompany(company);
	System.out.println("Halal");
}
}
