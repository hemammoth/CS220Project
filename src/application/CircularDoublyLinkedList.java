package application;

import java.util.ArrayList;

public class CircularDoublyLinkedList {
	private Node firstNode;
	private Node lastNode;
	private Node currentNode;
	private int size;
	ArrayList<String> names;
	
	public CircularDoublyLinkedList() {
		this.firstNode = null;
		this.lastNode = null;
		this.currentNode = null;
		size = 0;
		names = new ArrayList<String>();
	}
	
    public void add(String name) {
        Node newNode = new Node(name);   
        if (firstNode == null) {           
            newNode.next = newNode;
            newNode.prev = newNode;
            firstNode = newNode;
            lastNode = firstNode;  
            currentNode = firstNode;
        } else {
            newNode.prev = lastNode;
            lastNode.next = newNode;
            firstNode.prev = newNode;
            newNode.next = firstNode;
            firstNode = newNode; 
            currentNode = firstNode;
        }
        names.add(name);
        size++;
    }
    
    public void remove(int pos) {
    	if (currentNode != null && size != 1) {
    		prev();
    	}
    	
    	if (pos == 0) {
            if (size == 1) {
                firstNode = null;
                lastNode = null;
                size = 0;
                currentNode = null;
                return;
            }
            firstNode = firstNode.next;
            firstNode.prev = lastNode;
            lastNode.next = firstNode;
            size--;
            return ;
        } else if (pos == size-1) {
            lastNode = lastNode.prev;
            lastNode.next = firstNode;
            firstNode.prev = lastNode;
            size-- ;
        }
    	
        Node temp = firstNode.next;
        for (int i = 1; i < size; i++) {
            if (i == pos) {
                Node p = temp.prev;
                Node n = temp.next;

                p.next = n;
                n.prev = p;
                size-- ;
                return;
            }
            temp = temp.next;
        } 
    }
    
    public void next() {
    	currentNode = currentNode.next;
    }
    
    public void prev() {
    	currentNode = currentNode.prev;
    } 
    
    public Node getCurrentNode() {
    	return currentNode;
    }
    
	static class Node {
		Stack watchlist;
		Node next;
		Node prev;
		String name;
		static int index  = 0;
		
		public Node(String name) {
			this(name, index, null, null);
		}
		
		public Node(String name, int index, Node n, Node p) {
			watchlist = new Stack();
			this.name = name;
	        next = n;
	        prev = p;
	        index++;
	    }
	}
	
}

