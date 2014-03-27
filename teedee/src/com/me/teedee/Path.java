package com.me.teedee;

//import java.awt.Point;


/**
 * Describes the path that the enemy units will take when they travel through the map. 
 * 
 * @author dmich_000
 *
 */
public class Path {
	
	/**
	 * The coordinates that describes the path through the map from a top-down perspective
	 */
	private int[] xPos,yPos; 
	
	//private Point[] pos;
	
	/**
	 * Method that returns the x-coordinates
	 * @return the array of x-coordinates
	 */
	public int[] getXPos(){
		return xPos;
	}
	
	/**
	 * Method that returns the y-coordinates
	 * @return the array of y-coordinates
	 */
	public int[] getYPos(){
		return yPos;
	}
	
}
