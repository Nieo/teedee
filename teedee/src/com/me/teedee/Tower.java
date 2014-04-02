package com.me.teedee;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Nieo
 */
public abstract class Tower {
	protected Price[] price = new Price[1];
	protected int currentLevel;
	protected int maxLevel;
	protected int[] attackSpeed = new int[1]; 
	protected int[] attackDamage = new int[1];
	private double range;
	protected Status status;
	private int kills = 0;
	private Position position;
	protected ArrayList<AbstractEnemy> enemies;
	protected final int UPDATE_SPEED = 60;
	
	public Price getPrice(){
		return price[currentLevel];
	}
	public void setPosition(Position pos){
		position = pos;
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

	public void shoot(){
		AbstractEnemy target = null;
		if(UPDATE_SPEED%attackSpeed[currentLevel] == 0){
			for(int i = 1; i < enemies.size();i++){
				if(distance(enemies.get(i).getPosition()) < range ){
					if(target == null){
						target = enemies.get(i);
					}else{
						if(enemies.get(i).stepsTraveled() < target.stepsTraveled())
								target = enemies.get(i);
					}
				}
				
			}
			if(target != null){
				target.takeDamage(attackDamage[currentLevel], status);
				try{
				TimeUnit.MILLISECONDS.sleep(1000/attackSpeed[currentLevel]);
				}catch(InterruptedException e){
					
				}
			}
		}
	}
	private double distance(Position pos){
		float dx = position.getxCoordinate()- pos.getxCoordinate();
		float dy = position.getyCoordinate()- pos.getyCoordinate();
		return Math.sqrt((double)dx*dx+dy*dy);
	}
	
}
