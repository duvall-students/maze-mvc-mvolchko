package application;

import java.awt.Point;

import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithm;

public class MazeController {
	// Where to start and stop the search
	private Point start;
	private Point goal;
	
	//model and display
		private MazeDisplay mazeDisplay;
		private Maze maze;
	
	// The search algorithms
	//private Greedy greedy;				
	//private BFS bfs;
	//private DFS dfs;
	//private RandomWalk rand;
	//private Magic magic;
	private SearchAlgorithm search;		// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.
	
	
	
	public MazeController(int numRows, int numColumns, MazeDisplay display){
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
		mazeDisplay = display;
	}
	
	/*
	 * Re-create the maze from scratch.
	 * When this happens, we should also stop the search.
	 */
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		//search = "";
		mazeDisplay.redraw();
		search = null;
	}
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		//search = searchType;
		
		// Restart the search.  Since I don't know 
		// which one, I'll restart all of them.
		
		if(searchType.equals("DFS")) {
			search = new DFS(maze, start, goal);
		}
		else if (searchType.equals("BFS")) {
			search = new BFS(maze, start, goal);
		}
		else if (searchType.equals("Greedy")) {
			search = new Greedy(maze, start, goal);
		}
		else if (searchType.equals("RandomWalk")) {
			search = new RandomWalk(maze, start, goal);
		}
		else if (searchType.equals("Magic")) {
			search = new Magic(maze, start, goal);
		}

	}
	
	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		if (search!=null) {
			search.step();
		}
//		if(search.equals("DFS")) dfs.step();
//		else if (search.equals("BFS")) bfs.step();
//		else if (search.equals("Greedy")) greedy.step();
//		else if (search.equals("RandomWalk")) rand.step();
//		else if (search.equals("Magic")) magic.step();
		mazeDisplay.redraw();
	}
	
	public int getCellState(Point position) {
		return maze.get(position);
	}

}
