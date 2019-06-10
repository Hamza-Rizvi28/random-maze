package dsMaze;

import java.util.Random;

class Maze {

	static int startRow = 0;
	static int startCol = 0;
	static int currRow = 0;
	static int currCol = 0;

	static void generateMaze(Graph g) {
		try {
		for (int i = 0; i < Main.size; i++) {
			g.AddVertex(Integer.toString(i));
		}
		g.display(); // simple graph without edges
		settingBoundaries(g);
		int[] arr = new int[2];
		arr = Maze.startingCell(g, g.count);
		System.out.println(g.stk.peek());
		g.display();
		currRow = arr[0];
		currCol = arr[1];
		String str = startRow + "," + startCol;
		while (g.stk.isEmpty() == false && g.stk.peek() != str) {
			arr = Maze.randomMovement(g, currRow, currCol);
			currRow = arr[0];
			currCol = arr[1];
			System.out.println(g.stk.peek());
			// g.display();
		}
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("");
		}
	}

	static void settingBoundaries(Graph g) {
		for (int i = 0; i < g.count; i++) {
			for (int j = 0; j < g.count; j++) {
				if (i == 0 || j == 0 || i == g.count || j == g.count) {
					g.isVisited[i][j] = true;
				} else
					continue;
			}
		}
	}

	static int[] startingCell(Graph g, int size) {
		Random rand = new Random();
		int r = rand.nextInt(size);
		while (r % 2 == 0) {
			r = rand.nextInt(size);
		}
		// Generate random c
		int c = rand.nextInt(size);
		while (c % 2 == 0) {
			c = rand.nextInt(size);
		}
		int[] cell = new int[2];
		startRow = currRow = r;
		startCol = currCol = c;
		cell[0] = r;
		cell[1] = c;
		g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[1]));
		return cell;
	}

	static int[] generateRandomDirections() { // Fisher–Yates shuffle array function
		int index, temp;
		int[] array = { 1, 2, 3, 4 };
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
		return array;
	}

	static int[] randomMovement(Graph g, int r, int c) { // r is the current row index
															// c is the current column index
		int[] cell = new int[3];
		// 4 random directions
		int[] randDirs = generateRandomDirections();
		int count = 0;
		// Examine each direction
		for (int i = 0; i < randDirs.length; i++) {
			if (randDirs[i] == 1) { // Up
				// Whether 2 cells up is out or not
				if (r - 2 <= 0 || g.maze[r - 2][c] == 1) {
					count++;
					continue;
				}
				if (g.maze[r - 2][c] == 0 && g.isVisited[r - 2][c] == false) {
					cell[0] = r - 2;
					cell[1] = c;
					cell[2] = r - 1;
					g.AddEdge(Integer.toString(cell[2]), Integer.toString(cell[1])); // maze[r-1][c] = 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[1])); // maze[r-2][c] = 1;
					break;
				}
			}
			if (randDirs[i] == 2) {
				// Right
				// Whether 2 cells to the right is out or not
				if (c + 2 >= g.count || g.maze[r][c + 2] == 1) {
					count++;
					continue;
				}
				if (g.maze[r][c + 2] == 0 && g.isVisited[r][c + 2] == false) {
					cell[0] = r;
					cell[1] = c + 2;
					cell[2] = c + 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[2])); // maze[r][c + 1] = 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[1])); // maze[r][c + 2] = 1;
					break;
				}
			}

			if (randDirs[i] == 3) { // Down
				// Whether 2 cells down is out or not
				if (r + 2 >= g.count || g.maze[r + 2][c] == 1) {
					count++;
					continue;
				}
				if (g.maze[r + 2][c] == 0 && g.isVisited[r + 2][c] == false) {
					cell[0] = r + 2;
					cell[1] = c;
					cell[2] = r + 1;
					g.AddEdge(Integer.toString(cell[2]), Integer.toString(cell[1])); // maze[r+1][c] = 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[1])); // maze[r+2][c] = 1;
					break;
				}
			}
			if (randDirs[i] == 4) { // Left
				// Whether 2 cells to the left is out or not
				if (c - 2 <= 0 || g.maze[r][c - 2] == 1) {
					count++;
					continue;
				}
				if (g.maze[r][c - 2] == 0 && g.isVisited[r][c - 2] == false) {
					cell[0] = r;
					cell[1] = c - 2;
					cell[2] = c - 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[2])); // maze[r][c - 1] = 1;
					g.AddEdge(Integer.toString(cell[0]), Integer.toString(cell[1])); // maze[r][c - 2] = 1;
					break;
				}
			}
		}
		if (count == 4) {
			if (g.stk.top == 0) {
				g.stk.pop();
			} else {
				g.stk.pop();
				g.stk.pop();
				cell[0] = currRow = r = Integer.valueOf(g.stk.peek().substring(0, g.stk.peek().indexOf(",")));
				cell[1] = currCol = c = Integer.valueOf(g.stk.peek().substring(g.stk.peek().indexOf(",") + 1));
			}
		}
		return cell;
	}
}
