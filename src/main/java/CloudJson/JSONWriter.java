package CloudJson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import com.google.gson.Gson;
import Cloud.model.Client;
import Cloud.model.ClientDatabase;
import Cloud.model.LogisticCompany; 


/** This class is a part of the persistency layer and is used to access and change the status of the logistic company in the JSON-files.
 *
 */
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
  * @return client
  * @throws FileNotFoundException
  */
 public static Client getIn() throws FileNotFoundException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepathin);
	 Client client = gson.fromJson(reader, Client.class);
	 return client;
 }
 
 /** Updates the in.json file to contain a new client as logged in
  * 
  * @param client
  * @throws IOException
  */
 public static void setIn(Client client) throws IOException {
	 Gson gson = new Gson();
	 Writer fwin = new FileWriter("in.json");
	 gson.toJson(client, fwin);
     fwin.flush();
     fwin.close();
 }
 
 /** Checks password when user tries to log in
  * @param mail - string
  * @param password - string
  * @return boolean
  * @throws IOException
  */
 public static boolean checkPassword(String mail, String password) throws IOException {
	 Gson gson = new Gson();
	 Reader reader = new FileReader(filepath);
	 LogisticCompany company = gson.fromJson(reader, LogisticCompany.class);
	 ClientDatabase ClientDB = company.getClients();
	 Client client = ClientDB.getClient(mail);
	 if (client != null && (client.getPassword()).equals(password)) {
		 return true;
	 }
	 return false;
 }
 
}
