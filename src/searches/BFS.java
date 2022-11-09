package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import application.Maze;

public class BFS extends SearchAlgorithm{	

	// Keeps up with the child-parent trail so we can recreate the chosen path
	HashMap<Point,Point> childParent;



	public BFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		data = new LinkedList<>();
		data.add(startPoint);
		childParent = new HashMap<>();
	}

	/*
	 * Algorithm for Breadth-First Search
	 */
	
	
	protected void specificSearch() {
		Queue<Point> queue = (Queue<Point>) data;
		queue.remove();
	}

	/*
	 * This method defines which "neighbors" or next points can be reached in the maze from
	 * the current one.  
	 * 
	 * In this method, the neighbors are defined as the four squares immediately to the north, south,
	 * east, and west of the current point, but only if they are in the bounds of the maze.  It does 
	 * NOT check to see if the point is a wall, or visited.  
	 * 
	 * Any other definition of "neighbor" indicates the search subclass should override this method.
	 */


	/*
	 * This method defines the neighbor that gets chosen as the newest "fringe" member
	 * 
	 * It chooses the first point it finds that is empty.
	 */


	/*
	 * In addition to putting the new node on the data structure, 
	 * we need to remember who the parent is.
	 */
	protected void recordLink(Point next){	
		Queue<Point> queue = (Queue<Point>) data;
		queue.add(next);
		childParent.put(next,current);
	}

	/*
	 * The new node is the one next in the queue
	 */
	protected void resetCurrent(){
		Queue<Point> queue = (Queue<Point>) data;
		current = queue.peek();
	}


	/*
	 * Search is over and unsuccessful if there are no more fringe points to consider.
	 * Search is over and successful if the current point is the same as the goal.
	 */
	

	/*
	 * Use the trail from child to parent to color the actual chosen path
	 */
	protected void colorPath(){
		Point step = goal;
		maze.markPath(step);
		while(step!=null){
			maze.markPath(step);
			step = childParent.get(step);
		}
	}






}
