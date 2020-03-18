package CloudContainers;

import java.util.ArrayList;

public class Database {
	
		ArrayList<Client> arr = new ArrayList<Client>();
		private int count = 0;
		
		
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public ArrayList<Client> getArr() {
			return arr;
		}
		
		public void addClient(Client c) {
			if(!this.exist(c)) {
				arr.add(count, c);
				count++;
				
			}
		}
		
		public boolean exist(Client c) {
			boolean b;
			b = false;
			for (Client cl : arr) {
				b = (c.getEmail() == cl.getEmail()) || b;
			}
			return b;
		}
// public static void main(String[] args) {
//	Client client;
//	client = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
//	Database database = new Database();
//	database.addClient(client);
//	database.getArr().get(0);
//	
//	for(Client c  : database.arr) {
//		System.out.println(c.getEmail());
//	}
//	
//}
}
