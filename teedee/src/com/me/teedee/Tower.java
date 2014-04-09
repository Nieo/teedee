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
	protected final int UPDATE_SPEED = 60;
	private boolean isShooting = false;
	private Timer fireTimer = new Timer();
	
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
	public Boolean isShooting(){
		return isShooting;
	}
	
	//Is it OK to have this as an internal private class?
	private class ShootTask extends TimerTask{
		@Override
		public void run(){
			AbstractEnemy target = null;
			if(UPDATE_SPEED%attackSpeed[currentLevel] == 0){ //TODO How is this supposed to work?
				isShooting = true;
				for(int i = 1; i < enemies.size();i++){
					if(distance(enemies.get(i).getPosition()) < range && enemies.get(i).isAlive() ){
						if(target == null){
							target = enemies.get(i);
						}else{
							if(enemies.get(i).getStepsTraveled() < target.getStepsTraveled())
									target = enemies.get(i);
						}
					}
				}
				if(target != null){
					target.takeDamage(attackDamage[currentLevel], status);
					System.out.println("SHOT");
					System.out.println(target.toString());
				}
			}else{
				isShooting = false;
			}
		}
	}

	//Should probably be named startShooting instead, since it's something that SHOULD be going on for a period of time,
	//we don't want a new thread to be created every time this method is created, just once.
	public void shoot(){
		if(!isShooting){
			fireTimer.scheduleAtFixedRate(new ShootTask(), 0, 1000/attackSpeed[currentLevel]);
		}
	}
	
	private double distance(Position pos){
		float dx = position.getX()- pos.getX();
		float dy = position.getY()- pos.getY();
		return Math.sqrt((double)dx*dx+dy*dy);
	}
	
}
