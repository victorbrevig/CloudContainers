package CloudContainers;
public class Database {
		Client[] arr = new Client[10];
		int count = 0;
		
		
		public Client[] getArr() {
			return arr;
		}
		
		public void addClient(Client c) {
			arr[count] = c;
			count++;
		}
		
		public boolean exist(Client c) {
			boolean b;
			b = false;
			for (int i = 0; i< 10; i++) {
				b = (arr[i]).getEmail() == c.getEmail() || b;
			}
			return b;
		}

}
