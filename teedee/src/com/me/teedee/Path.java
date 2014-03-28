package com.me.teedee;


/**
 * Describes the path that the enemy units will take when they travel through the map. 
 * 
 * @author Fridgeridge
 *
 */
public class Path {
	
	/**
	 * The coordinates that describes the path through the map from a top-down perspective
	 */
	private Position[] pos;
	
	/**
	 * Constructs a path utilizing a list of positions
	 * @param pos The list of positions to be used
	 */
	public Path(Position[] pos){
		this.setPos(pos);
	}
	
	/**
	 * Returns the array of positions
	 * @return The array of positions
	 */
	public Position[] getPos() {
		return pos;
	}
	/**
	 * Sets the positions
	 * @param pos Array of positions to be set
	 */
	public void setPos(Position[] pos) {
		this.pos = pos;
	}
	
}
