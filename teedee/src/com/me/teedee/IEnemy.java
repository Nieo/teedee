package com.me.teedee;
/**
 * The interface of the enemy class. Provides the body to be 
 * used and implemented by enemy units.
 * 
 * @author Fridgeridge
 *
 */
public interface IEnemy {

		
	
	/**
	 * Enemy takes damage when invoked
	 */
	public void takeDamage(int damage);
	
	
	/**
	 * Enemy takes damage and gets a status effect when invoked
	 */
	public void takeDamage(int damage, Status s);
	
	
	/**
	 * Enemy moves forward in the path
	 */
	public void move();
	
	/**
	 * Enemy dies,awards player money and 
	 * disappears from the map.
	 */
	public void die();
	
	/**
	 * Method that checks if the enemy has end of path next move
	 * 
	 * @return true if end of path is the next move 
	 */
	
	public boolean hasNextEnd();
	
	/**
	 * Gets the money reward from an enemy
	 * 
	 * @return Reward money 
	 */
	public Reward getReward();
	
	/**
	 * Sets a status effect to the enemy unit
	 * @param s The status effect to be set to the enemy
	 */
	public void setStatusEffect(Status s);
	
	/**
	 * Gets the current status effect from the enemy unit
	 * 
	 */
	public Status getStatusEffect();
	
	/**
	 * Method that returns information about the enemy unit
	 * @return A string message containing information about the enemy unit
	 */
	public String toString();
	
	
}
