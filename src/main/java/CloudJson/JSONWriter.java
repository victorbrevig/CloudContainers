package CloudJson;

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

import javax.servlet.RequestDispatcher;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import Cloud.model.Client;
import Cloud.model.ClientDatabase;
import Cloud.model.LogisticCompany; 

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
 
 /**Needs to retrieve the logged in client from the file in.json
  * 
  * @return
  * @throws FileNotFoundException
  */
 
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
 
 public static boolean checkPassword(String mail, String pass) throws IOException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepath);
	 LogisticCompany company = gson.fromJson(reader, LogisticCompany.class);
	 ClientDatabase ClientDB = company.getClients();
	 Client client = ClientDB.getClient(mail);
	 if (client != null && (client.getPassword()).equals(pass)) {
		 return true;
	 }
	 return false;
 }
 
}
