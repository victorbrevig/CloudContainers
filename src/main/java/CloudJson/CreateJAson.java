package CloudJson;

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
import Cloud.model.LogisticCompany; 


public class CreateJAson {
 public static void main(String[] args) throws IOException {
     System.out.println("Test1");
	 Gson gson = new Gson();
	 Writer lw = new FileWriter("lc.json");

	 LogisticCompany company = new LogisticCompany("Maersk",1,2,"bigstonks");

	 gson.toJson(company, lw);

     lw.flush();
     lw.close();
     System.out.println("Test2");
}

}
