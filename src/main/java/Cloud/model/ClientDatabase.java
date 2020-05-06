package Cloud.model;

import java.util.HashSet;

import Cloud.model.Client;

/** Represents a hashset of clients
 * @author Gustav
 * @author Victor
 *
 */

public class ClientDatabase extends HashSet<Client> {

	private static final long serialVersionUID = 1L;

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
		

	
		
}
