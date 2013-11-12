package org.tappoz.main;

import java.io.IOException;
import java.util.ArrayList;

import org.tappoz.pojo.Maze;
import org.tappoz.pojo.MazeMatch;
import org.tappoz.pojo.Point;
import org.tappoz.pojo.SolvedMazeSummary;
import org.tappoz.util.LoadMazeMatch;

public class MazeMainRun {

	public static void main(String[] args) 
	{
		if(args.length == 0)
	    {
			System.out.println("You should specify a filename to run this command line tool!");
			System.exit(0);
	    }
		String filePath = args[0];
		
		// --------------------------------------------------
		
		try 
		{
			MazeMatch thisMazeMatch = LoadMazeMatch.getMazeMatch(filePath);
			Point thisStartingPoint = thisMazeMatch.getStartingPoint();
			Point thisEndingPoint = thisMazeMatch.getEndingPoint();
			
			if(thisMazeMatch.findPath())
			{
				Maze winningMaze = thisMazeMatch.getMaze();
				ArrayList<Point> winningPath = thisMazeMatch.getSolutionPath();
				
				SolvedMazeSummary thisSolvedMaze = new SolvedMazeSummary(winningMaze.getNumOfRows(), winningMaze.getNumOfColumns());
				thisSolvedMaze.setOriginalSolvedMaze(winningMaze);
				thisSolvedMaze.setSolvedPath(winningPath);
				thisSolvedMaze.setStartingPoint(thisStartingPoint);
				thisSolvedMaze.setEndingPoint(thisEndingPoint);
				
//				log.info("We found a path: \n" + thisMazeMatch.getSolutionPath().toString());
//				log.info("The Solved Maze is: \n" + thisSolvedMaze.toString());
				
				System.out.println("The Solved Maze is: \n" + thisSolvedMaze.toString());
			}
		} 
		catch (IOException e) 
		{
			//log.info("\n ++++++++++++++++++++++++++++++++++++++++++ \n There is no file at this file path: \n" + filePath);
			System.err.println("No input file!");
		}
	}

}
