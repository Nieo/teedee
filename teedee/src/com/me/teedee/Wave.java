package com.me.teedee;

import java.util.ArrayList;

/*
 * @Author Nieo
 */
public class Wave {
	
	private int waveSize = 0;
	private ArrayList<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();
	
	public Wave(Path p,int[] t){
		
		for(int amount: t)
			waveSize += amount;
		for(int i = 0; i < t.length;i++){
			addEnemies(i, t[i], p);
		}
	}
	public int getWaveSize(){
		return waveSize;
	}
	
	public ArrayList<AbstractEnemy> getEnemies(){
		return enemies;
		
	}
	
	private void addEnemies(int type, int amount,Path p){
		switch(type){
		case 0:
			for(int i = 0;i < amount;i++){
				enemies.add(new BasicEnemy(p));
			}
			break;
		default:
			System.out.println("Someone have tried to add enemytypes that do not exist.");
			break;
		}
	}
	
}
