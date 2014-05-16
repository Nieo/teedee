package com.me.teedee;

import java.util.ArrayList;
import java.util.Collections;

import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.enemies.BasicEnemy;
import com.me.teedee.enemies.DaBossEnemy;
import com.me.teedee.enemies.FastEnemy;
import com.me.teedee.enemies.ShieldEnemy;
import com.me.teedee.enemies.SlowEnemy;

/**
 * @Author Nieo
 */
public class Wave {

	private int waveSize = 0;
	private ArrayList<AbstractEnemy> enemies = new ArrayList<AbstractEnemy>();

	public Wave(Path p,int[] t,int level){
		for(int amount: t)
			waveSize += amount;
		for(int i = 0; i < t.length;i++){
			addEnemies(i, t[i], p,level);
		}
	}

	public int getWaveSize(){
		return waveSize;
	}

	public ArrayList<AbstractEnemy> getEnemies(){
		Collections.shuffle(enemies);
		return enemies;
	}

	private void addEnemies(int type, int amount,Path p, int level){
		switch(type){
		case 0:
			for(int i = 0;i < amount;i++){
				enemies.add(new BasicEnemy(p,level));
			}
			break;

		case 1:
			for(int i = 0; i < amount; i++){
				enemies.add(new FastEnemy(p,level));
			}
			break;

		case 2:
			for(int i = 0; i < amount; i++){
				enemies.add(new SlowEnemy(p,level));
			}
			break;
		case 3:
			for(int i = 0; i < amount; i++){
				enemies.add(new ShieldEnemy(p,level));
			}
			break;
		case 4:
			for(int i = 0; i < amount; i++){
				enemies.add(new DaBossEnemy(p, level));
			}
			break;
		default:
			System.out.println("Someone have tried to add enemytypes that do not exist.");
			break;
		}
	}
}
