package com.me.teedee;
/*
 * @Author Nieo
 */
public class Lives {
	private int currentLives;
	private int maxLives;
	
	public Lives(){
		maxLives = 10;
		currentLives = 10;
	}
	public Lives(int lives){
		maxLives = lives;
		currentLives = lives;
	}
	/**
	 * Returns true if currentLives is greater than 0 
	 */
	public boolean lowerLives(int damage){
		currentLives = currentLives - damage;
		return currentLives >  0;
	}
	
	public int getLivesHealth() {
		return currentLives;
	}
	public int getMaxLives() {
		return maxLives;
	}
	
}
