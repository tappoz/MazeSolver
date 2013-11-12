package org.tappoz.pojo;

public class ExploredMaze implements MetaMazeStructure {

	private boolean[][] alreadyExploredMazeArea;
	private int numOfRows;
	private int numOfColumns;
	
	// --------------------------------------------------
	
	public ExploredMaze(int inputNumOfRows, int inputNumOfColumns) {
		this.numOfRows = inputNumOfRows;
		this.numOfColumns = inputNumOfColumns;
		this.alreadyExploredMazeArea = new boolean[numOfRows][numOfColumns];
	}

	// --------------------------------------------------

	public int getNumOfRows() {
		return numOfRows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
	// --------------------------------------------------

	public void setAsExplored(int inputRowIndex, int inputColumnIndex) {
		this.alreadyExploredMazeArea[inputRowIndex][inputColumnIndex] = true;
	}

	// --------------------------------------------------

	public boolean getValue(int inputRowIndex, int inputColumnIndex) {
		return alreadyExploredMazeArea[inputRowIndex][inputColumnIndex];
	}
	// --------------------------------------------------
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
	    builder.append("\n--- The already explored Maze --- \n");
	    for(int i = 0; i < numOfRows; i++)
	    {
	        for(int j = 0; j < numOfColumns; j++)
	        {
	        	if (alreadyExploredMazeArea[i][j])
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
