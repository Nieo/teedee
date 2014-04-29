package com.me.teedee;

import java.util.*;

/**
 * @author Nieo
 */
public abstract class AbstractTower {
	protected Price[] price = new Price[5];
	protected int value;
	protected int currentLevel;
	protected int maxLevel;
	protected int[] attackSpeed = new int[5]; 
	protected int[] attackDamage = new int[5];
	protected double range;
	protected Status status;
	private int kills = 0;
	private Position position;
	protected ArrayList<AbstractEnemy> enemies;
	protected final int UPDATE_SPEED = 60; //Number of updates per second
	private boolean isShooting = false;
	private int updateCounter = 1;
	private AbstractEnemy target;
	protected String name;
	protected int id;
	
	public Price getBuildPrice() {
		return price[currentLevel];
	}
	public Price getUpgradePrice() {
		if(currentLevel < maxLevel-1)
			return price[currentLevel+1];
		return new Price(Integer.MAX_VALUE);
	}
	
	public Position getTargetPosition() {
		if(hasTarget()) {
			return new Position(target.getPosition().getX(),target.getPosition().getY());
		} else {
			return new Position(0,0); //TODO Should this throw an exception instead?
		}
	}
	
	public void setEnemies(ArrayList<AbstractEnemy> enemies) {
		this.enemies = enemies;
	}
	
	public boolean hasTarget() {
		return !(target == null);
	}
	
	public void setPosition(Position pos) {
		position = pos;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public int getKills() {
		return kills;
	}
	
	public int getId() {
		return id;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Boolean upgrade() {
		if(currentLevel < maxLevel - 1) {
			currentLevel++;
			value += price[currentLevel].getPrice();
			return true;
		}
		return false;
	}
	
	public Boolean isShooting() {
		return isShooting;
	}

	//Should probably be named startShooting instead, since it's something that SHOULD be going on for a period of time,
	//we don't want a new thread to be created every time this method is created, just once.
	public void shoot() {
		if(updateCounter%(UPDATE_SPEED/attackSpeed[currentLevel]) == 0) {
			target = null;
			//isShooting = true;
			for(int i = 0; i < enemies.size(); i++) {
				if(distance(position, enemies.get(i).getPosition()) < range && enemies.get(i).isAlive()) {
					if(target == null) {
						target = enemies.get(i); 
					} else {
						if(enemies.get(i).getStepsTraveled() > target.getStepsTraveled())
								target = enemies.get(i);
					}
				}
			}
			
			if(target != null) {
				isShooting = true;
				//TODO maybe do this in another way
				if(!target.takeDamage(attackDamage[currentLevel], status)) {
					kills++;
				}
			}
			updateCounter = 1;
		} else {
			isShooting = false;
		}
		updateCounter++;
	}
	
	public static double distance(Position p1, Position p2) {
		float dx = p1.getX()- p2.getX();
		float dy = p1.getY()- p2.getY();
		return Math.sqrt((double)dx*dx+dy*dy);
	}
	
	
}
