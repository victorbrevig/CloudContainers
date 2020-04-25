package Cloud.model;

import java.util.HashSet;

import Cloud.model.Client;

/** Represents a hashset of clients
 * @author Gustav
 * @author Victor
 *
 */

public class ClientDatabase extends HashSet<Client> {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/** Gets a client given a unique clientID
		 * 
		 * @param clientID
		 * @return client
		 */
	
		public Client getClient(int clientID) {
			for (Client c :this) {
				if (c.getClientID() == clientID) {
					return c;
				}
			} return null;
		}
		
		/** Gets client given a unique email
		 * 
		 * @param email
		 * @return client
		 */
		
		public Client getClient(String email) {
			
			for (Client c :this) {
				if ((c.getEmail()).equals(email)) {
					return c;
				}
			} return null;
		}	
		

	
		
}
