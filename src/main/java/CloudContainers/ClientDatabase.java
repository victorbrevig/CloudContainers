package CloudContainers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ClientDatabase extends HashSet<Client> {
		
		
		public Client getClient(int clientID) {
			for (Client c :this) {
				if (c.getClientID() == clientID) {
					return c;
				}
			} return null;
		}
		
		public Client getClient(String email) {
			
			for (Client c :this) {
				if ((c.getEmail()).equals(email)) {
					return c;
				}
			} return null;
		}	
		public Client getClientN(int number) {
			
			for (Client c :this) {
				if (c.getNumber()==number) {
					return c;
				}
			} return null;
		}
	
		
}
