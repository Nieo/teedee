package com.me.teedee;
/**
 * 
 * @author Jacob Genander
 *
 * A class representing the status of an enemy. The status affects the enemy by 
 * changing its speed, and may also cause damage during a period of time.
 */

public class Status {
	private float speedRatio;
	private float damagePerSecond;
	private float influenceTime;
	private float timeLeft;

	/**
	 * Constructs a default status
	 */
	public Status(){
		this(1,0,0);
	}

	/**
	 * Constructs a status with the given speed, damage per second and time of imfluence
	 * @param speed the new speed of the enemy
	 * @param damagePerSecond the damage the enemy will take per second during the status time of influence
	 * @param influenceTime the time in seconds that the status will have influence on the enemy
	 */
	public Status(float speedRatio, float damagePerSecond, float influenceTime){
		this.speedRatio = speedRatio;
		this.damagePerSecond = damagePerSecond;
		this.influenceTime = influenceTime;
		this.timeLeft = influenceTime;
	}

	/**
	 * creating a copy of the given status and its current state (including the "time left" value)
	 * @param status the status from which the new status will be created
	 */
	public Status(Status status){
		this.speedRatio = status.getSpeedRatio();
		this.damagePerSecond = status.getDamagePerSecond();
		this.influenceTime = status.getInfluenceTime();
		this.timeLeft = status.timeLeft;
	}

	/**
	 * 
	 * @return the status' applied speed
	 */
	public float getSpeedRatio(){
		return speedRatio;
	}

	/**
	 * 
	 * @return the status' applied damage per second
	 */
	public float getDamagePerSecond(){
		return damagePerSecond;
	}

	/**
	 * 
	 * @return the status' applied time of influence
	 */
	public float getInfluenceTime(){
		return influenceTime;
	}

	/**
	 * 
	 * @return how many seconds the status will affect the enemy before it disappears
	 */
	public float getTimeLeft(){
		return timeLeft;
	}

	/**
	 * 
	 * Reducing the time that's left for the status to affect the enemy
	 * @param time the time, in seconds, to reduce the status' time left
	 */
	public void reduceTimeLeft(float time){
		this.timeLeft -= time;
	}

	public void resetTime(){
		this.timeLeft = this.influenceTime;
	}
}
