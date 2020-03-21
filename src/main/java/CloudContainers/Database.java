package CloudContainers;

import java.util.ArrayList;

public class Database {
	
		protected ArrayList<Client> arr = new ArrayList<Client>();
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
		
		public int searchEmailIndex(String email) {
			// Returns -1 if client with email does not exist
			int i = -1;
			for (Client c : this.arr) {
				if (c.getEmail().equals(email)) {
					i = arr.indexOf(c);
				}
			}
			return i;
		}
		public int searchPhoneNumberIndex(String number) {
			// Returns -1 if client with phone number does not exist
			int i = -1;
			for (Client c : this.arr) {
				if (c.getNumber().equals(number)) {
					i = arr.indexOf(c);
				}
			}
			return i;
		}
		
		public Client searchClientID(int id) {
			Client client;
			try {
				for (Client c : this.arr) {
					if (c.getClientID() == id) {
						client = c;
					}
				}
			} catch (Exception e) {
				System.out.println("Client with this ID does not exist");
			}
			return client;
			
		}
		

}
