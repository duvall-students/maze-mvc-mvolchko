package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import application.Maze;

public class RandomWalk extends SearchAlgorithm{
	public final double EXPLORE_BIAS = .999;
	private Point next;
	private Random rand;
	
	
	public RandomWalk(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		next = startPoint;
		rand = new Random();
	}
	
	/*
	 * Algorithm for a Random Walk: Randomly choose a neighbor to go to
	 */

	
	
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
	 * Choose a random neighbor out of all the non-wall neighbors. 
	 * To make the algorithm prefer going to unexplored areas, make the EXPLORE_BIAS higher.
	 */
	
	@Override
	protected Point chooseNeighbor(Collection<Point> neighbors){
		List<Point> empties = new ArrayList<>();
		List<Point> possibles = new ArrayList<>();
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				empties.add(p);
			}
			if(maze.get(p) != Maze.WALL){	// includes empties - all candidates
				possibles.add(p);
			}
		}
		if((rand.nextDouble()<EXPLORE_BIAS && !empties.isEmpty())) {
			int randIndex = rand.nextInt(empties.size());
			return empties.get(randIndex);
		}
		if(!possibles.isEmpty()){
			int randIndex = rand.nextInt(possibles.size());
			return possibles.get(randIndex);
		}
		return null;
	}
	
	protected void recordLink(Point next){	
		this.next = next;
		maze.markVisited(current);
	}
	
	protected void resetCurrent(){
		current = next;
	}
	
	/*
	 * Search is over and unsuccessful if there are no more fringe points to consider.
	 * Search is over and successful if the current point is the same as the goal.
	 */

	

	protected void colorPath(){}


	protected void specificSearch() {}
	
}
