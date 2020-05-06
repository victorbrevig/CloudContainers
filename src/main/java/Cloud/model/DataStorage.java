package Cloud.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**Class stores an arrayList of Datapoints
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
public class DataStorage extends ArrayList<DataPoint> {

	private static final long serialVersionUID = 1L;

	
	public ArrayList<Double> extractTemperature() {
		
		ArrayList<Double> temperature = new ArrayList<Double>();
		
		
		for (DataPoint dp : this) {
			temperature.add(dp.getTemperature());
		}
		
		return temperature;
		
	}
	
	public ArrayList<Double> extractTime() {
		
		ArrayList<Double> time = new ArrayList<Double>();
		
		
		for (DataPoint dp : this) {
			time.add(dp.getTime());
		}
		
		return time;
		
	}
	
	public ArrayList<Double> extractPressure() {
		
		ArrayList<Double> pressure = new ArrayList<Double>();
		
		
		for (DataPoint dp : this) {
			pressure.add(dp.getPressure());
		}
		
		return pressure;
		
	}
	
	public ArrayList<Double> extractAirHum() {
		
		ArrayList<Double> airHum = new ArrayList<Double>();
		
		
		for (DataPoint dp : this) {
			airHum.add(dp.getAirHumidity());
		}
		
		return airHum;
		
	}
	
	
}
