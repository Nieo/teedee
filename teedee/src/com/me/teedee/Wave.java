package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Nieo
 */
public class Wave {
	
	private int nbrEnemies = 0;
	private List<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();
	private ArrayList<AbstractEnemy> enemyTypes = new ArrayList<AbstractEnemy>();
	
	public Wave(Path p,int[] t){
		
		for(int amount: t)
			nbrEnemies += amount;
		for(int i = 1; i <= t.length;i++){
			addEnemies(i, t[i], p);
		}
	}
	public int getKills(){
		return nbrEnemies;
	}
	
	public List<AbstractEnemy> getEnemies(){
		return enemies;
		
	}
	
	private void addEnemies(int type, int amount,Path p){
		switch(type){
		case 1:
			for(int i = 0;i < amount;i++){
				enemies.add(new BasicEnemy(p));
			}
			break;
		default:
			System.out.println("Someone have tried to add enemytypes that do not exist");
			break;
		}
	}
	
}
