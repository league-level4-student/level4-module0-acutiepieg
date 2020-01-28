package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int r = randGen.nextInt(maze.cells.length);
		int r2 = randGen.nextInt(maze.cells[r].length);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(r, r2));

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.hasBeenVisited();

		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> unvisited = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (unvisited.size() > 0) {
			// C1. select one at random.
			int r = randGen.nextInt(unvisited.size());
			// C2. push it to the stack
			uncheckedCells.push((unvisited.get(r)));
			// C3. remove the wall between the two cells
			removeWalls(currentCell, unvisited.get(r));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = unvisited.get(r);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if(uncheckedCells.size() != 0) {
			// D1a. pop a cell from the stack
				
			// D1b. make that the current cell

			// D1c. call the selectNextPath method with the current cell
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}
