package Cloud.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import Cloud.model.Client;
import Cloud.model.JSONWriter; 


public class CreateJAson {
 public static void main(String[] args) throws IOException {
     System.out.println("yeet");

	 Gson gson = new Gson();
	 Writer fw = new FileWriter("Y.json");
	 Writer aw = new FileWriter("Loggedin.json");
	 HashSet <Client> database = new HashSet <Client> ();
     gson.toJson(database, fw);
     gson.toJson(database,aw);
 	 Client client = new Client("mo","JegErMO","12","male",28381812,"12");
     JSONWriter jw = new JSONWriter();   
     jw.addClient(client);

     fw.flush();
     fw.close();
     aw.flush();
     aw.close();
     System.out.println("yeet");
}

}
