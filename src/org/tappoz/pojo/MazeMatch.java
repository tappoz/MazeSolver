package org.tappoz.pojo;

import java.util.ArrayList;

import org.tappoz.util.CoordinateTypeEnum;

public class MazeMatch {

	private Maze maze;
	private Point startingPoint;
	private Point endingPoint;
	private ExploredMaze alreadyExploredMazeArea;
	
	private ArrayList<Point> solutionPath;

	// --------------------------------------------------
	
	public MazeMatch(Maze inputMaze, Point inputStartingPoint, Point inputEndingPoint) {
		this.maze = inputMaze;
		this.startingPoint = inputStartingPoint;
		this.endingPoint = inputEndingPoint;
		this.solutionPath = new ArrayList<Point>();
		this.alreadyExploredMazeArea = new ExploredMaze(inputMaze.getNumOfRows(),inputMaze.getNumOfColumns());
	}

	// --------------------------------------------------
	
	public Maze getMaze() {
		return maze;
	}
	public void setMaze(Maze inputMaze) {
		this.maze = inputMaze;
	}

	// --------------------------------------------------
	
	public Point getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(Point inputStartingPoint) {
		this.startingPoint = inputStartingPoint;
	}

	// --------------------------------------------------
	
	public Point getEndingPoint() {
		return endingPoint;
	}
	public void setEndingPoint(Point inputEndingPoint) {
		this.endingPoint = inputEndingPoint;
	}
	
	// --------------------------------------------------
	
	public boolean findPath()
	{
//		log.info("Starting recursion to find the path.");
		
		if (findPath(startingPoint, endingPoint, maze, alreadyExploredMazeArea))
			return true;
		
		return false;
	}
	
	// --------------------------------------------------
	
	public boolean findPath(Point sPoint, Point ePoint, Maze runningMaze, ExploredMaze currentAlreadyExploredMazeArea)//boolean[][] currentAlreadyExploredMazeArea)
	{
//		log.info(		"\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" 
//					+	"\n   Current sPoint: " + sPoint.toString()
//					+	"\n   Current ePoint: " + ePoint.toString()	
//					+	"\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" 
//					+	"\n   Running Maze: "
//					+	runningMaze.toString()
//					+	"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++" 
//				);
//		log.info("Setting the Maze Area as visited for this sPoint: " + sPoint.toString());

		currentAlreadyExploredMazeArea.setAsExplored(sPoint.getRowIndex(),sPoint.getColumnIndex());
//		log.info(this.alreadyExploredAreaToString());
		
		// outside of the maze
		if	(		sPoint.getRowIndex() < 0 
				||	sPoint.getColumnIndex() < 0 
				||	sPoint.getRowIndex() >= runningMaze.getNumOfRows() 
				||	sPoint.getColumnIndex() >= runningMaze.getNumOfColumns()
			)	
		{
//			log.info(		"Outside of the maze for Starting " 
//						+ 	sPoint.toString() 
//						+ 	" and Ending " 
//						+ 	ePoint.toString() 
//						+ 	" and Maze dimensions: rows "
//						+	runningMaze.getNumOfRows() 
//						+ 	", columns: "
//						+	runningMaze.getNumOfRows());
			return false;
		}
		
		//	reached the ending point
		if( sPoint.equals(ePoint) )
		{
//			log.info("We found a Starting Point which equals Ending Point");
			return true;
		}
		
		//	finding a wall
		if( runningMaze.getPointValue(sPoint) == CoordinateTypeEnum.WALL )
		{
//			log.info("This Point is a WALL: " + sPoint.toString());
			return false;
		}
		
		//	marking this point as part of the solution
//		log.info("Adding to the Solution Path sPoint: " + sPoint.toString());
		solutionPath.add(sPoint);

		//	going north
		Point northernPoint = sPoint.getNorth();
		boolean isThisNorthernPointAlreadyVisited = currentAlreadyExploredMazeArea.getValue(northernPoint.getRowIndex(), northernPoint.getColumnIndex());
//		log.info("Going North inspecting: " + northernPoint.toString());
		if	(
					!isThisNorthernPointAlreadyVisited
				&&	findPath(northernPoint, ePoint, runningMaze, currentAlreadyExploredMazeArea)
			)
		{
			return true;
		}
//		else 
//		{
////			log.info("   >>> this Point has aready been visited: "  + northernPoint.toString());
//		}
		
		// going east
		Point easternPoint = sPoint.getEast();
		boolean isThisEasternPointAlreadyVisited = currentAlreadyExploredMazeArea.getValue(easternPoint.getRowIndex(), easternPoint.getColumnIndex());
//		log.info("Going East inspecting: " + easternPoint.toString());
		if	(
					!isThisEasternPointAlreadyVisited
				&&	findPath(easternPoint, ePoint, runningMaze, currentAlreadyExploredMazeArea)
			)
		{
			return true;
		}
//		else 
//		{
//			log.info("   >>> this Point has aready been visited: "  + easternPoint.toString());
//		}
		
		// going south
		Point southernPoint = sPoint.getSouth();
		boolean isThisSouthernPointAlreadyVisited = currentAlreadyExploredMazeArea.getValue(southernPoint.getRowIndex(), southernPoint.getColumnIndex());
//		log.info("Going South inspecting: " + southernPoint.toString());
		if	(
					!isThisSouthernPointAlreadyVisited
				&&	findPath(southernPoint, ePoint, runningMaze, currentAlreadyExploredMazeArea)
			)
		{
			return true;
		}
//		else 
//		{
//			log.info("   >>> this Point has aready been visited: "  + southernPoint.toString());
//		}
		
		// going west
		Point westernPoint = sPoint.getWest();
		boolean isThisWesternPointAlreadyVisited = currentAlreadyExploredMazeArea.getValue(westernPoint.getRowIndex(), westernPoint.getColumnIndex());
//		log.info("Going West inspecting: " + westernPoint.toString());
		if	(
					!isThisWesternPointAlreadyVisited
				&&	findPath(westernPoint, ePoint, runningMaze, currentAlreadyExploredMazeArea)
			)
		{
			return true;
		}
//		else 
//		{
//			log.info("   >>> this Point has aready been visited: "  + westernPoint.toString());
//		}
		
		// found an unuseful point that needs to be removed from the solution path
//		log.info("Removing from the Solution Path sPoint: " + sPoint.toString());
		solutionPath.remove(sPoint);
		
		return false;
	}
	
	// --------------------------------------------------
	
	public ArrayList<Point> getSolutionPath() {
		return solutionPath;
	}
	
	// --------------------------------------------------
	
	public String alreadyExploredAreaToString()
	{
		StringBuilder builder = new StringBuilder();
	    builder.append("\n--- The already explored Maze --- \n");
	    for(int i = 0; i < maze.getNumOfRows(); i++)
	    {
	        for(int j = 0; j < maze.getNumOfColumns(); j++)
	        {
	        	if (alreadyExploredMazeArea.getValue(i,j))
	        		builder.append("Y").append(' ');
	        	else
	        		builder.append("N").append(' ');
	        }
	        builder.append('\n');
	    }
	    builder.append("--- The already explored Maze --- \n");
	    return builder.toString();
	}
	
}
