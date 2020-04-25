package Cloud.model;
//package CloudContainers;
//
////Java program for write JSON to a file 
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.io.Writer;
//import java.util.HashSet;
//import java.util.LinkedHashMap; 
//import java.util.Map; 
//import org.json.simple.JSONArray; 
//import org.json.simple.JSONObject;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonIOException; 
//
//public class JSONWriter{ 
//	
// public JSONWriter() throws IOException {
//	 Gson gson = new Gson();
//	 Writer fww = new FileWriter("Y.json");
//	 HashSet <Client> database2 = new HashSet <Client>();
//	 gson.toJson(database2, fww);
//     fww.flush();
//     fww.close();
// }
// public void addClient(Client client) throws IOException{
//	 Gson gson = new Gson();
//	 Reader reader = new FileReader("Y.json");
//	 Database db = gson.fromJson(reader, Database.class);
//	 Writer fw = new FileWriter("Y.json");
//	 HashSet <Client> database = db ;
//	 database.add(client);
//     gson.toJson(database, fw);
//     fw.flush();
//     fw.close();
// }
// public void test() {
//	 System.out.println("yeet");
// }
// public static void main(String[] args) throws IOException {
//	 JSONWriter wr = new JSONWriter();
//	 Client client = new Client("Bob1",001,"mediumman1@dtu.dk","22","male","10101010");
//	 Client client2 = new Client("Marley",002,"bigman2@dtu.dk","22","male","10101010");
//	 wr.addClient(client);
//	 wr.addClient(client2);
//	 wr.test();
//}
//}
