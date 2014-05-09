package com.me.teedee.enemies;

import com.me.teedee.Lives;
import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Reward;

/**
 * A most basic enemy, extends abstract enemy
 * @author Fridgeridge
 *
 */
public class BasicEnemy extends AbstractEnemy {
	
	
	private final int id = 1;
	
	public BasicEnemy(Path p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public BasicEnemy(Path p, Position pos) {
		super(p,120f,new Lives(100),new Reward(50),pos);
		//TODO
	}
	
	public BasicEnemy(BasicEnemy b) {
		super(b);
	}

	@Override
	public int getId() {
		return id;
	}
	
}
