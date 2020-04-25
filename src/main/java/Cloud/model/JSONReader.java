package Cloud.model;
//package CloudContainers;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.HashSet;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonIOException;
//import com.google.gson.JsonSyntaxException;
//
//public class JSONReader
//{
//    public static void main(String args[]) throws JsonSyntaxException, JsonIOException, FileNotFoundException
//    {
//    	 Gson gson = new Gson();
//
//         try (Reader reader = new FileReader("Yeet.json")) {
//
//             // Convert JSON File to Java Object
//        	 Database db = gson.fromJson(reader, Database.class);
// 			
// 			// print staff 
//             System.out.println(db);
//
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         }
//}