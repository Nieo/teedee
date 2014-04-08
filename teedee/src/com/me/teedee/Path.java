package com.me.teedee;

import java.util.*;

/**
 * Describes the path that the enemy units will take when they travel through the map. 
 * 
 * @author Fridgeridge
 *
 */
public class Path implements Iterator {

	/**
	 * The coordinates that describes the path through the map from a top-down perspective
	 */
	private List <Position> pos;
	private Iterator<Position> list;



	//	private int currentPosIndex;


	/**
	 * Constructs a path utilizing a list of positions
	 * @param pos The list of positions to be used
	 */
	public Path(List<Position> pos) {
		//		this.setCurrentPosIndex(0);
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

	//	/**
	//	 * Returns the current single position of the position array
	//	 * @return A single position
	//	 */
	//	public Position getCurrentPos(){
	//		return this.getPositions().get(getCurrentPosIndex());
	//	}

	//	/**
	//	 * Returns the current index of the position array
	//	 * @return The current index
	//	 */
	//	public int getCurrentPosIndex() {
	//		return currentPosIndex;
	//	}

	//	/**
	//	 * Sets the current position index
	//	 * @param currentPos The new index to be set
	//	 */
	//	private void setCurrentPosIndex(int currentPos) {
	//		this.currentPosIndex = currentPos;
	//	}

	@Override
	public boolean hasNext() {
		//Iterator<Position> list = getPositions().iterator();
		return list.hasNext();   
		/*if(this.getPositions().get(this.getCurrentPosIndex()+1)!=null){
			return true;//Not sure if this is a failproof check, probably needs work.
		}else{
		return false;
		}*/
	}

	@Override
	public Position next() {
		//Iterator<Position> list = getPositions().iterator();
		if(list.hasNext()) {
			return (Position) list.next();
		} else {
			list.remove();
			return (Position) this.next();
		}

		/*if(this.hasNext()){//Again, not sure if this is a correct way of handling things
		this.setCurrentPosIndex(currentPosIndex+1);	
		return this.getPositions().get(this.getCurrentPosIndex());
		}else{
			throw new NoSuchElementException();
		}*/
	}

	@Override
	public void remove() {
		Iterator<Position> list = getPositions().iterator();
		list.remove();
	}

}
