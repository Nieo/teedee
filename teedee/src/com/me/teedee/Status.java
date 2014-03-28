package com.me.teedee;
/**
 * 
 * @author Jacob Genander
 *
 * A class representing the status of an enemy. The status affects the enemy by 
 * changing its speed, and may also cause damage during a period of time.
 */

public abstract class Status {
	private double speed;
	private double damagePerSecond;
	private double influenceTime;
	
	/**
	 * Constructs a default status
	 */
	public Status(){
		speed = 1;
		damagePerSecond = 0;
		influenceTime = 0;
	}
	
	/**
	 * Constructs a status with the given speed, damage per second and time of imfluence
	 * @param speed the new speed of the enemy
	 * @param damagePerSecond the damage the enemy will take per second during the status time of influence
	 * @param influenceTime the time in milliseconds that the status will have influence on the enemy
	 */
	public Status(double speed, double damagePerSecond, double influenceTime){
		this.speed = speed;
		this.damagePerSecond = damagePerSecond;
		this.influenceTime = influenceTime;
	}

	/**
	 * 
	 * @return the status' applied speed
	 */
	public double getSpeed(){
		return speed;
	}
	
	/**
	 * 
	 * @return the status' applied damage per second
	 */
	public double getDamagePerSecond(){
		return damagePerSecond;
	}
	
	/**
	 * 
	 * @return the status' applied time of influence
	 */
	public double getInfluenceTime(){
		return influenceTime;
	}
}
