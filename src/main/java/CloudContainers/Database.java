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
		
		public int searchEmail(String email) {
			// Returns -1 if client with email does not exist
			int i = -1;
			for (Client c : this.arr) {
				if (c.getEmail() == email) {
					i = arr.indexOf(c);
				}
			}
			return i;
		}

}
