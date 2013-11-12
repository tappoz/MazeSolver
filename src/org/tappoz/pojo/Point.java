package org.tappoz.pojo;

public class Point {

	private int rowIndex;
	private int columnIndex;
	
	public Point(int inputRowIndex, int inputColumn) {
		super();
		this.rowIndex = inputRowIndex;
		this.columnIndex = inputColumn;
	}
	
	// --------------------------------------------------
	
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int inputRowIndex) {
		this.rowIndex = inputRowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int inputColumnIndex) {
		this.columnIndex = inputColumnIndex;
	}

	// --------------------------------------------------
	
	public Point getNorth() throws UnsupportedOperationException
	{
		return new Point(rowIndex - 1, columnIndex);
	}
	
	public Point getSouth() throws UnsupportedOperationException
	{
		return new Point(rowIndex + 1, columnIndex);
	}
	
	public Point getEast() throws UnsupportedOperationException
	{
		return new Point(rowIndex, columnIndex + 1);
	}
	
	public Point getWest() throws UnsupportedOperationException
	{
		return new Point(rowIndex, columnIndex - 1);
	}
	
	// --------------------------------------------------
	
	@Override
	public String toString() {
		return "Point [rowIndex=" + rowIndex 
				+ ", columnIndex=" + columnIndex
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnIndex;
		result = prime * result + rowIndex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (columnIndex != other.columnIndex)
			return false;
		if (rowIndex != other.rowIndex)
			return false;
		return true;
	}
}
