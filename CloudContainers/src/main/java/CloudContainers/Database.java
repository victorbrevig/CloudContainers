package CloudContainers;

import java.util.ArrayList;

public class Database {
		ArrayList<Client> arr = new ArrayList<Client>();
		int count = 0;
		
		
		public ArrayList<Client> getArr() {
			return arr;
		}
		
		public void addClient(Client c) {
			this.arr.set(count, c);
			count++;
		}
		
		public boolean exist(Client c) {
			boolean b = false;
			
			for (Client cl : arr) {
				b = (c.getEmail() == cl.getEmail()) || b;
			}
			return b;
		}
		
		public static void main(String[] args) {
			Client client;
			client = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
			Database database = new Database();
			database.addClient(client);
			
			for (Client c : database.arr) {
				System.out.println(c.getEmail());
			}
		}

}


