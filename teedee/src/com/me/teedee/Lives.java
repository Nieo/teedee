package com.me.teedee;

/**
 * Lives is a class which represent the health of enemies
 * @Author Nieo
 */
public class Lives {
	private float currentLives;
	private float maxLives;

	public Lives(){
		maxLives = 1000;
		currentLives = 1000;
	}

	public Lives(float lives){
		maxLives = lives;
		currentLives = lives;
	}

	/**
	 * @return true if currentLives is greater than 0 
	 */
	public boolean lowerLives(float damage){
		currentLives = currentLives - damage;
		return currentLives >  0;
	}

	public float getCurrentLives() {
		return currentLives;
	}

	public float getMaxLives() {
		return maxLives;
	}
}
