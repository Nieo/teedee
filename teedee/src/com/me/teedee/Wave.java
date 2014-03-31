package com.me.teedee;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Nieo
 */
public class Wave {
	
	private int nbrEnemies;
	private List<AbstractEnemy> enemies;
	private ArrayList<AbstractEnemy> enemyTypes;
	
	public Wave(int t1){
		nbrEnemies = t1;
	}
	
	
	public List<AbstractEnemy> getEnemies(){
		return enemies;
		
	}
	
	
}
