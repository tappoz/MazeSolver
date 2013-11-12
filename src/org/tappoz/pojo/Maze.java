package org.tappoz.pojo;

import org.tappoz.util.CoordinateTypeEnum;

public class Maze {

	private int[][] mazeArea;
	private int numOfRows;
	private int numOfColumns;

	// --------------------------------------------------
	
	private static final String MAZE_POINT_NOT_SET = "Cannot set the Maze for these values: ";
	private static final String MAZE_OUT_OF_BOUND = "Maze indexes out of bound for this: ";
	private static final String COORDINATE_TYPE = " and this Coordinate Type inputValue: ";

	// --------------------------------------------------

	public Maze(int inputNumOfRows, int inputNumOfColumns) 
	{
		this.numOfRows = inputNumOfRows;
		this.numOfColumns = inputNumOfColumns;
		this.mazeArea = new int[inputNumOfRows][inputNumOfColumns];
	}
	
	// --------------------------------------------------

	public void setPointValue(Point inputPoint, int inputValue) throws UnsupportedOperationException
	{
		if 
		(		mazeArea.length > inputPoint.getRowIndex() 
			&& 	mazeArea[0].length > inputPoint.getColumnIndex() 
			&& 	CoordinateTypeEnum.isMember(inputValue)
		)
			this.mazeArea[inputPoint.getRowIndex()][inputPoint.getColumnIndex()] = inputValue;
		else throw new UnsupportedOperationException(MAZE_POINT_NOT_SET + inputPoint.toString()  + COORDINATE_TYPE + inputValue);
	}
	
	public CoordinateTypeEnum getPointValue(Point inputPoint)
	{
		return CoordinateTypeEnum.getEnumValue(mazeArea[inputPoint.getRowIndex()][inputPoint.getColumnIndex()]);
	}
	
	// --------------------------------------------------
	
	public boolean isWall(Point inputPoint) throws UnsupportedOperationException
	{
		if (mazeArea.length > inputPoint.getRowIndex() && mazeArea[0].length > inputPoint.getColumnIndex())
			return (mazeArea[inputPoint.getRowIndex()][inputPoint.getColumnIndex()] == CoordinateTypeEnum.WALL.getCode());
		else throw new UnsupportedOperationException(MAZE_OUT_OF_BOUND + inputPoint.toString());
	}
	
	public boolean isPassage(Point inputPoint) throws UnsupportedOperationException
	{
		if (mazeArea.length > inputPoint.getRowIndex() && mazeArea[0].length > inputPoint.getColumnIndex())
		{
			int currentMazePointValue = mazeArea[inputPoint.getRowIndex()][inputPoint.getColumnIndex()];
			return (currentMazePointValue == CoordinateTypeEnum.PASSAGE.getCode());
		}
		else throw new UnsupportedOperationException(MAZE_OUT_OF_BOUND + inputPoint.toString());
	}
	
	// --------------------------------------------------

	public int getNumOfRows() {
		return numOfRows;
	}

	public int getNumOfColumns() {
		return numOfColumns;
	}
	
	// --------------------------------------------------

	@Override
	public String toString()
	{
	    StringBuilder builder = new StringBuilder();
	    builder.append("\n--- The Maze --- \n");
	    for(int i = 0; i < getNumOfRows(); i++)
	    {
	        for(int j = 0; j < getNumOfColumns(); j++)
	        {
	            builder.append(mazeArea[i][j]).append(' ');
	        }
	        builder.append('\n');
	    }
	    builder.append("--- The Maze --- \n");
	    return builder.toString();
	}
}
