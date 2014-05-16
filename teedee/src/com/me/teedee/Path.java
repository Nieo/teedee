package com.me.teedee;

import java.util.Iterator;
import java.util.List;

/**
 * Describes the path that the enemy units will take when they travel through the map. 
 * @author Fridgeridge
 */
public class Path {

	/**
	 * The coordinates that describes the path through the map from a top-down perspective
	 */
	private List <Position> pos;
	private Iterator<Position> list;

	/**
	 * Constructs a path utilizing a list of positions
	 * @param pos The list of positions to be used
	 */
	public Path(List<Position> pos) {
		this.setPositions(pos);
		list = getPositions().iterator();
	}

	/**
	 * Returns the array of positions
	 * @return The array of positions
	 */
	public List<Position> getPositions() {
		return pos;
	}

	/**
	 * Sets the positions
	 * @param pos2 List of positions to be set
	 */
	public void setPositions(List<Position> pos2) {
		this.pos = pos2;
	}

	public boolean hasNext() {
		return list.hasNext();   
	}

	public Position next() {
		if(list.hasNext()) {
			return list.next();
		} else {
			list.remove();
			return this.next();
		}
	}

	public void remove() {
		Iterator<Position> list = getPositions().iterator();
		list.remove();
	}
}
