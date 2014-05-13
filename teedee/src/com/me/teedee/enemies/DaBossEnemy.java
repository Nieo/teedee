package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;

public class DaBossEnemy extends AbstractEnemy {

	private final int id = 5;
	
	public DaBossEnemy(Path p){
		super(p);
		
	}

	public DaBossEnemy(Path p, Position pos) {
		super(p,120f,new Lives(100),new Reward(50),pos);
		//TODO
	}
	
	public DaBossEnemy(DaBossEnemy b) {
		super(b);
	}
	public void updateEnemy(){
		this.setLives(new Lives(50000));
		this.setReward(new Reward(1000));
	}
	@Override
	public int getId() {
		return id;
	}
}
