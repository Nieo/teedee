package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;

// 50% faster than a regular enemy
public class FastEnemy extends AbstractEnemy {
	
	private final int id = 2;
	
	public FastEnemy(Path p){
		super(p);
		updateFastEnemy();
	}
	
	public FastEnemy(Path p, Position pos) {
		super(p, 120f,new Lives(100),new Reward(50),pos);
		updateFastEnemy();
	}
	
	public FastEnemy(FastEnemy f){
		super(f);
		updateFastEnemy();
	}
	
	private void updateFastEnemy(){
		this.setSpeed(this.getSpeed()*1.5f);
		int nr=(int) (this.getEnemyReward().getReward()*1.3);
		this.setReward( new Reward(nr));
		int nl= (int) (this.getLives().getCurrentLives()*0.7);
		this.setLives(new Lives(nl));
	}
	
	@Override
	public int getId(){
		return id;
	}

}
