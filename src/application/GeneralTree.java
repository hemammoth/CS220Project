package application;
import java.util.ArrayList;
import java.util.Iterator;

public class GeneralTree {
	Node root;

	public GeneralTree(char letter) {
		root = new Node(null, letter, false);
	}

	public ArrayList<String> getTickerList(String ticker) {
		Node child;
		Node parent = root;

		for (int i = 0; i<ticker.length(); i++) {
			child = parent.find(ticker.charAt(i));
			if (parent.children.contains(child)) {
				parent = child;
			} else {
				return null;
			}
		}

		return DFS(parent);
	}

	public ArrayList<String> DFS(Node node, ArrayList<String> visited, ArrayList<String> tickerList) { 
		// Mark the current node as visited  
		visited.add(node.toString()); 

		if (node.ticker) {
			tickerList.add(node.toString());
		}

		// Recur for all the vertices adjacent to this vertex 
		Iterator<Node> i = node.children.listIterator(); 
		while (i.hasNext()) { 
			Node n = i.next(); 
			if (!visited.contains(n.toString())) 
				DFS(n, visited, tickerList); 
		}

		return tickerList; 
	} 

	// The function to do DFS traversal. It uses recursive DFSUtil() 
	public ArrayList<String> DFS(Node node) { 
		// Mark all the vertices as not visited(set as 
		// false by default in java) 
		ArrayList<String> visited = new ArrayList<String>(); 
		ArrayList<String> tickerList = new ArrayList<String>();

		// Call the recursive helper function to print DFS traversal 
		return DFS(node, visited, tickerList); 
	} 

	public void insert(String ticker) {
		Node parent = root;
		Node child = null;
		int tickerLength = ticker.length();

		for (int i = 0; i<tickerLength; i++) {
			child = parent.find(ticker.charAt(i));
			
			if (child == null) {
				child = new Node(parent, ticker.charAt(i), false);
				parent.children.add(child);
			}	
				
			if(i == tickerLength-1) {
				child.setTicker(true);
			} else {
				parent = child;
			}

		}
	}
	
	public void remove(String ticker) {
		Node child;
		Node parent = root;

		for (int i = 0; i<ticker.length(); i++) {
			child = parent.find(ticker.charAt(i));
			if (parent.children.contains(child)) {
				parent = child;
			} else {
				return;
			}
		}
		
		parent.ticker = false;
	}

	public class Node {
		private char letter;
		private boolean ticker;
		private Node parent;
		private ArrayList<Node> children;

		public Node (Node parent, char letter, boolean ticker) {
			this.parent = parent;
			this.letter = letter;
			this.ticker = ticker;
			this.children = new ArrayList<Node>();
		}

		public void setTicker(boolean ticker) {
			this.ticker = ticker;
		}
		
		public Node find(char letter) {

			for (Node child : this.children) {
				if (child.letter == letter) {
					return child;
				}
			}

			return null; // Not found.
		}

		@Override
		public String toString() {
			Node parent = this.parent;
			String tickerSymbol = letter + "";
			
			if (letter == '$') { 
				return null;
			}

			while (parent.letter != '$') {
				tickerSymbol = parent.letter + tickerSymbol;
				parent = parent.parent;
			}

			return tickerSymbol;
		}
	}

}
