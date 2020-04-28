package Cloud.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.HashSet;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import Cloud.model.Client;
import Cloud.model.JSONWriter; 


public class CreateJAson {
 public static void main(String[] args) throws IOException {
     System.out.println("yeet");

     
     //ObjectMapper objectMapper = new ObjectMapper();
     
     
     
	 Gson gson = new Gson();
	 Writer fw = new FileWriter("Y.json");
	 Writer aw = new FileWriter("Loggedin.json");
	 Writer lw = new FileWriter("company.json");
	
	 
	 HashSet <Client> database = new HashSet <Client> ();
	 
	 
	 LogisticCompany company = new LogisticCompany("Maersk",1,2,"bigstonks");
	 
	 Client cli = new Client("Karsten","smallmoney123@gmail.com","24-05-1998","male",10101010,"1234");

	 company.newClient(cli);
	 
	 //objectMapper.writeValue(Paths.get("company2.json").toFile(), company);
	 
	 
	 
	 //LogisticCompany company2 = objectMapper.readValue(new File("C:\\Users\\victo\\git\\CloudContainers\\company2.json"), LogisticCompany.class);
	 
	 gson.toJson(company, lw);
	 
	 
     gson.toJson(database, lw);
     gson.toJson(database,aw);
     //gson.toJson(company,lw);
 	 Client client = new Client("mo","JegErMO","12","male",28381812,"12");
     JSONWriter jw = new JSONWriter();   
     //jw.addClient(client);

     fw.flush();
     fw.close();
     aw.flush();
     aw.close();
     lw.flush();
     lw.close();
     System.out.println("yeet");
}

}
