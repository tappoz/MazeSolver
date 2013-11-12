package org.tappoz.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.tappoz.pojo.Maze;
import org.tappoz.pojo.MazeMatch;
import org.tappoz.pojo.Point;

public class LoadMazeMatch {
	
	private static final String SPLITTING_VALUE = " ";
	
	public static MazeMatch getMazeMatch(String inputFilePath) throws IOException {

		// get the input file
        FileInputStream inputStream = new FileInputStream(inputFilePath);
        DataInputStream in = new DataInputStream(inputStream);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		
        // build the Maze area
        String[] firstLine = bf.readLine().split(SPLITTING_VALUE);
        Maze thisMaze = new Maze(Integer.parseInt(firstLine[0]),Integer.parseInt(firstLine[1]));
        
        // build the starting Point
        String[] secondLine = bf.readLine().split(SPLITTING_VALUE);
        Point startingPoint = new Point(Integer.parseInt(secondLine[0]),Integer.parseInt(secondLine[1]));
        
        // build the starting Point
        String[] thirdLine = bf.readLine().split(SPLITTING_VALUE);
        Point endingPoint = new Point(Integer.parseInt(thirdLine[0]),Integer.parseInt(thirdLine[1]));
        
        // fill the Maze with Wall/Passage values
        String line = "";
        int lineCount = 0;
        while ((line = bf.readLine()) != null)
        {
            String[] mazeLineValues = line.split(" ");
			for ( int i = 0 ; i < mazeLineValues.length ; i++) 
			{
				int pointValue = Integer.parseInt(mazeLineValues[i]);
				Point currentPoint = new Point(lineCount, i);
				
            	if (CoordinateTypeEnum.isMember(pointValue))
            		thisMaze.setPointValue(currentPoint, pointValue);
            	else 
            	{
            		bf.close();
        			throw new UnsupportedOperationException(	"This Point for this Maze: " 
        													+ 	currentPoint.toString() 
        													+ 	" has this invalid value: "
        													+	pointValue);
            	}
			}
            lineCount++;
        }
        
        bf.close();
        
        return new MazeMatch(thisMaze, startingPoint, endingPoint);
	}
}
