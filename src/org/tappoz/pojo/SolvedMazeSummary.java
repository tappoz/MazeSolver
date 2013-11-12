package org.tappoz.pojo;

import java.util.ArrayList;

public class SolvedMazeSummary implements MetaMazeStructure {

	private String[][] solvedMaze;
	private int numOfRows;
	private int numOfColumns;
	
	// --------------------------------------------------
	
	public SolvedMazeSummary(int inputNumOfRows, int inputNumOfColumns) 
	{
		this.numOfRows = inputNumOfRows;
		this.numOfColumns = inputNumOfColumns;
		this.solvedMaze = new String[inputNumOfRows][inputNumOfColumns];
		
		for(int i = 0; i < numOfRows; i++)
	    {
	        for(int j = 0; j < numOfColumns; j++)
	        {
	        	solvedMaze[i][j] = "!";
	        }
	    }
	}

	// --------------------------------------------------
	
	public int getNumOfRows() {
		return numOfRows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
	// --------------------------------------------------
	
	public void setOriginalSolvedMaze(Maze winningMaze)
	{
		for(int i = 0; i < numOfRows; i++)
	    {
	        for(int j = 0; j < numOfColumns; j++)
	        {
	        	Point currentPoint = new Point(i,j);
	        	if (winningMaze.isPassage(currentPoint))
	        	{
	        		solvedMaze[i][j] = " ";
	        	}
	        	else // is WALL
	        	{
	        		solvedMaze[i][j] = "#";
	        	}
	        }
	    }
	}
	
	// --------------------------------------------------

	public void setStartingPoint(Point startingPoint)
	{
		solvedMaze[startingPoint.getRowIndex()][startingPoint.getColumnIndex()] = "S";
	}

	// --------------------------------------------------
	
	public void setEndingPoint(Point endingPoint)
	{
		solvedMaze[endingPoint.getRowIndex()][endingPoint.getColumnIndex()] = "E";
	}
	
	// --------------------------------------------------
	
	public void setSolvedPath(ArrayList<Point> solvedPath)
	{
		for(Point thisPoint : solvedPath)
		{
			solvedMaze[thisPoint.getRowIndex()][thisPoint.getColumnIndex()] = "X";
		}
	}
	
	// --------------------------------------------------
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
	    for(int i = 0; i < numOfRows; i++)
	    {
	        for(int j = 0; j < numOfColumns; j++)
	        {
	        	builder.append(solvedMaze[i][j]);
	        }
	        builder.append('\n');
	    }
	    return builder.toString();
	}

}
