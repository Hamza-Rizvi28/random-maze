package dsMaze;

class Stack {

	int maxSize;
	String[] StackArr;
	int top;

	public Stack() {
		maxSize =250;
		StackArr = new String[maxSize];
		top = -1;
	}

	public Stack(int size) {
		maxSize = size;
		StackArr = new String[maxSize];
		top = -1;
	}

	public void push(String ch) {
		if (isFull()) {
			System.out.println(" Stack Overflow!");
		} else {
			top++;
			StackArr[top] = ch;
		}
	}

	public String pop() {
		return StackArr[top--];
	}

	public String peek() {
		if (top == -1)
			return "";
		else
			return StackArr[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return top == maxSize - 1;
	}
}
