package com.me.teedee;

import java.util.*;

/*
 * @Author Nieo
 */
public abstract class Tower {
	private Price price;
	private int currentLevel;
	private int maxLevel;
	private int[] attackSpeed;
	private int[] attackDamage;
	private int[] range;
	private Status status;
	private int kills = 0;
	private int xCoord;
	private int yCoord;
	
	public int getPrice(){
		//TODO
		return 0;
	}
	public int getCurrentLevel(){
		return currentLevel;
	}
	public int getMaxLevel(){
		return maxLevel;
	}
	public int getKills(){
		return kills;
	}
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
	public Boolean upgrade(){
		if(currentLevel < maxLevel){
			currentLevel++;
			return true;
		}
		return false;
	}
	public void shoot(List<IEnemy> enemies){
		while(!enemies.isEmpty()){
			List inRange = new ArrayList();
			for(IEnemy e : enemies){
				//TODO
				/* if inrange add to list
				 * find longestDistanceTraveled
				 * e.takeDamage(attackDamage,Status);
				 * sleep
				 */
			}
		}
	}
	
}
