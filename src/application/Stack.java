package application;

import java.util.ArrayList;

public class Stack {
	private ArrayList<String> stack;
	private int index;
	private String top;
	
	public Stack() {
		this.stack = new ArrayList<String>();
		this.index = -1;
		this.top = null;
	}

	public void push(String ticker) {
		if (!stack.contains(ticker)) {
			index++;
			stack.add(ticker);
			top = stack.get(index);
		}
	}
	
	public void pop() {
		if (index >= 1) {
			stack.remove(index);
			index--;
			top = stack.get(index);
		} else if (index == 0) {
			stack.remove(index);
			index--;
			top = null;
		}
	}
	
	public String peek() {
		String topNode;
		if (top != null) {
			topNode = top;
		}
		else {
			topNode = null;
		}
		return topNode;
	}
	
	public int getIndex() {
		return index;
	}
	
	public ArrayList<String> getStack() {
		return stack;
	}
}
