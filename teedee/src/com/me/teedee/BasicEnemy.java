package com.me.teedee;

/**
 * A most basic enemy, extends abstract enemy
 * @author Fridgeridge
 *
 */
public class BasicEnemy extends AbstractEnemy {

	public BasicEnemy(Path p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public BasicEnemy(Path p, float sp, Lives l, Reward r, Status s, Position pos) {
		super(p,sp,l,r,s,pos);
		//TODO
	}
	
	public BasicEnemy(BasicEnemy b) {
		super(b);
	}
	
}
