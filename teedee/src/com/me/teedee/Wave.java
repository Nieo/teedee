package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Nieo
 */
public class Wave {
	
	private int nbrEnemies;
	private List<AbstractEnemy> enemies;
	private ArrayList<AbstractEnemy> enemyTypes = new ArrayList<AbstractEnemy>();
	
	public Wave(Path p,int[] t){
		enemyTypes.add(new BasicEnemy(p));
		
		nbrEnemies = t.length;
		for(int i = 0;i < nbrEnemies; i++){
			for(int j = 0; j < t[i];j++){
				
			}
		}
	}
	
	
	public List<AbstractEnemy> getEnemies(){
		return enemies;
		
	}
	
	
}
