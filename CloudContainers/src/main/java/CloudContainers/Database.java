package CloudContainers;

import java.util.ArrayList;

public class Database {
		ArrayList<Client> arr = new ArrayList<Client>();
		int count = 0;
		
		
		public ArrayList<Client> getArr() {
			return arr;
		}
		
		public void addClient(Client c) {
			arr.set(count, c);
			count++;
		}
		
		public boolean exist(Client c) {
			boolean b;
			b = false;
			for (int i = 0; i< 10; i++) {
				b = (arr.get(i)).getEmail() == c.getEmail() || b;
			}
			return b;
		}

}
