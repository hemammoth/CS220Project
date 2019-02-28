package application;

public class HashMap { 
	private HashNode[] hashMap; //HashMap of nodes 
	private int size;  // Current size of array list
	private final int MAX_CAPICITY; // Maximum capacity of hash map

	public HashMap() { 
		size = 0; 
		MAX_CAPICITY = 10000;
		hashMap = new HashNode[MAX_CAPICITY]; 
	} 

	public int getSize() { 
		return size; 
	} 

	// This implements hash function to find index for a key 
	private int getMapIndex(String key) { 
		int hashCode = key.hashCode(); 
		int index = hashCode % MAX_CAPICITY; 
		return index; 
	} 

	// Returns value for a key 
	public StockInfo get(String key) { 
		// Find head of linked list for given key 
		int index = getMapIndex(key); 
		HashNode head = hashMap[index]; 

		// Search key in linked list 
		while (head != null) { 
			if (head.key.equals(key)) 
				return head.value; 
			head = head.next; 
		} 

		// If key not found 
		return null; 
	} 

	// Adds a key value pair to hash 
	public void add(String key, StockInfo value) { 
		// Find head of chain for given key 
		int index = getMapIndex(key); 
		HashNode head = hashMap[index]; 

		// Check if key is already present 
		while (head != null) { 
			// If key already exists it replaces its value with new value and escapes method
			if (head.key.equals(key)) { 
				head.value = value; 
				return; 
			} 
			head = head.next; 
		} 

		size++; 
		// Insert key in chain 
		head = hashMap[index]; 
		HashNode newNode = new HashNode(key, value); 
		newNode.next = head; 
		hashMap[index] = newNode; 
	}
	
	public void remove(String key) { 
        // Apply hash function to find index for given key 
        int index = getMapIndex(key); 
  
        // Get head of chain 
        HashNode head = hashMap[index]; 
  
        // Search for key in its chain 
        HashNode prev = null; 
        while (head != null) { 
            // If Key found 
            if (head.key.equals(key)) {
            	break; 
            }
  
            // Else keep moving in chain 
            prev = head; 
            head = head.next; 
        } 
  
        // If key was not there 
        if (head == null) {
            return; 
        }

        size--; 
  
        // Remove key 
        if (prev != null) {
        	prev.next = head.next; 
        } else {
        	hashMap[index] = head.next; 
        }
  
        return; 
    } 

	class HashNode { 
		String key; 
		StockInfo value; 
		HashNode next; 

		public HashNode(String key, StockInfo value) { 
			this.key = key; 
			this.value = value; 
		} 
	} 
}