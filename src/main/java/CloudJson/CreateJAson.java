package CloudJson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import com.google.gson.Gson;
import Cloud.model.LogisticCompany; 

/** This class is an JSON-file creator. This should be run only to restart the state of the program with a new logistic company. 
 * 
 */
public class CreateJAson {
 public static void main(String[] args) throws IOException {
     System.out.println("Test1");
	 Gson gson = new Gson();
	 Writer lw = new FileWriter("lc.json");

	 LogisticCompany company = new LogisticCompany("Maersk",50,"password");

	 gson.toJson(company, lw);

     lw.flush();
     lw.close();
     System.out.println("Test2");
}

}
