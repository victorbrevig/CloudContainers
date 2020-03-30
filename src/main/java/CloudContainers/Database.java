package CloudContainers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Database extends HashSet<Client> {
		
		Client emptyClient = new Client("",0,"","","",0,"");
		public Client getClient(int clientID) {
			for (Client c :this) {
				if (c.getClientID() == clientID) {
					return c;
				}
			} return emptyClient;
		}
		
		public Client getClient(String email) {
			
			for (Client c :this) {
				if ((c.getEmail()).equals(email)) {
					return c;
				}
			} return emptyClient;
		}	
		public Client getClientN(int number) {
			
			for (Client c :this) {
				if (c.getNumber()==number) {
					return c;
				}
			} return emptyClient;
		}
	
		
}
