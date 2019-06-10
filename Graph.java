package dsMaze;

class Vertex {
	String label;
	boolean isVisited;

	Vertex() {
		label = "";
		isVisited = false;
	}

	Vertex(String label) {
		this.label = label;
		isVisited = false;
	}
}

class Graph {

	Vertex[] V;
	int[][] maze;
	int count;
	boolean[][] isVisited;
	Stack stk = new Stack();

	Graph(int s) {

		V = new Vertex[s];
		maze = new int[s][s];
		isVisited = new boolean[s][s];
		count = -1;

	}

	public void AddVertex(String label) {
		Vertex v = new Vertex(label);
		if (count < V.length) {
			V[++count] = v;
		} else {
			System.out.println("No more vertex can be added!");
		}
	}

	public void AddEdge(String l1, String l2) {
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i <= count; i++) {
			if (l1.equals((V[i].label))) {
				index1 = i;
			}
			if (l2.equals((V[i].label))) {
				index2 = i;
			}
		}
		maze[index1][index2] = 1;
		isVisited[index1][index2] = true;
		stk.push(index1 + "," + index2);

	}

	public void display() {
		/*
		 * for (int i = 0; i <= count; i++) { System.out.println("vertex: " +
		 * V[i].label); } System.out.println();
		 */
		for (int i = 0; i <= count; i++) {
			for (int j = 0; j <= count; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("\n");
	}
}
