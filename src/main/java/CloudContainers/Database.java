package CloudContainers;

import java.util.ArrayList;

public class Database {
	
		protected ArrayList<Client> arr = new ArrayList<Client>();
		private int count = 0;
//		private String errorMessage;
//
//		public String getErrorMessage() {
//			return errorMessage;
//		}
//
//		public void setErrorMessage(String errorMessage) {
//			this.errorMessage = errorMessage;
//		}
		
		
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

		 
		
		// The method used for the search here is not tested finished, might use client = null instead
		public Client searchClientID(int id) {
			
			Client client = new Client("",0,"","","","");
			for (Client c : this.arr) {
					if (c.getClientID() == id) {
						client = c;
						return client;
					}
				}System.out.println("Client with this client ID does not exist");
			return client;
			
			
		}
		public Client searchEmail(String email) {
			Client client = new Client("",0,"","","","");
				for (Client c : this.arr) {
					if (c.getEmail().equals(email)) {
						client = c;
						return client;
					}
			}System.out.println("Client with this email does not exist");
		return client;
		}
		
		
}
