package CloudContainers;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class toCSV {
	static FileWriter csvWriter;
	
	public static void writeCSV(String fileName, double time, double temp, double pressure, double airHum) {
		try {
		    String comma = ",";
			String timeStr = time + "";
		    String tempStr = temp + "";
		    String pressureStr = pressure + "";
		    String airHumStr = airHum + "";
		     
		    FileOutputStream outputStream = new FileOutputStream("C:\\Users\\victo\\git\\CloudContainers\\JourneyStatusData\\" + fileName, true);
		    byte[] strToBytes = timeStr.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = comma.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = tempStr.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = comma.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = pressureStr.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = comma.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = airHumStr.getBytes();
		    outputStream.write(strToBytes);
		    strToBytes = "\n".getBytes();
		    outputStream.write(strToBytes);
		    
		    outputStream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
