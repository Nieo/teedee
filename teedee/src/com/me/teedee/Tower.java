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
	protected double range;
	protected Status status;
	private int kills = 0;
	private Position position;
	protected ArrayList<AbstractEnemy> enemies;
	protected final int UPDATE_SPEED = 60; //Number of updates per second
	private boolean isShooting = false;
	private int updateCounter = 1;
	private AbstractEnemy target;
	private boolean hasTarget = false;
	
	public Price getPrice(){
		return price[currentLevel];
	}
	public Position getTargetPosition(){
		if(hasTarget){
			return new Position(target.getPosition().getX(),target.getPosition().getY());
		}else
			return new Position(0,0); //TODO Should this throw an exception instead?
	}
	public boolean hasTarget(){
		return target!=null;
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
	public Boolean isShooting(){
		return isShooting;
	}

	//Should probably be named startShooting instead, since it's something that SHOULD be going on for a period of time,
	//we don't want a new thread to be created every time this method is created, just once.
	public void shoot(){
		if(attackSpeed[currentLevel]*updateCounter%UPDATE_SPEED == 0){
			isShooting = true;
			for(int i = 1; i < enemies.size();i++){
				if(distance(enemies.get(i).getPosition()) < range && enemies.get(i).isAlive() ){
					//if(target == null){ //This should never be needed, since the first enemy is selected on the next if-statement
						target = enemies.get(i); 
					//}else{
						if(enemies.get(i).getStepsTraveled() < target.getStepsTraveled())
								target = enemies.get(i);
					//}
				}
			}
			if(target != null){
				target.takeDamage(attackDamage[currentLevel], status);
				System.out.println("SHOT");
				System.out.println(target.toString());
			}
			updateCounter = 1;
		}else{
			isShooting = false;
		}
		updateCounter++;
	}
	
	private double distance(Position pos){
		float dx = position.getX()- pos.getX();
		float dy = position.getY()- pos.getY();
		return Math.sqrt((double)dx*dx+dy*dy);
	}
	
	
}
