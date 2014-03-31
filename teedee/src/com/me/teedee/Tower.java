package com.me.teedee;

import java.util.*;

/**
 * @author Nieo
 */
public abstract class Tower {
	private Price[] price;
	private int currentLevel;
	private int maxLevel;
	private int[] attackSpeed;
	private int[] attackDamage;
	private int range;
	private Status status;
	private int kills = 0;
	private Position position;
	
	public Price getPrice(){
		//TODO
		return null;
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
	public Position getPosition(){
		return position;
	}
	public Boolean upgrade(){
		if(currentLevel < maxLevel){
			currentLevel++;
			return true;
		}
		return false;
	}
	public void shoot(List<AbstractEnemy> enemies){
		while(!enemies.isEmpty()){
			AbstractEnemy target = null;
			for(AbstractEnemy e : enemies){
				if(inRange(e.getPosition())){
					
				}
				/* if inrange add to list
				 * find longestDistanceTraveled
				 * e.takeDamage(attackDamage,Status);
				 * sleep
				 */
			}
			
		}
	}
	private boolean inRange(Position pos){
		int dx = position.getxCoordinate()- pos.getxCoordinate();
		int dy = position.getyCoordinate()- pos.getyCoordinate();
		return range*range > dx*dx+dy*dy;
	}
	
}
