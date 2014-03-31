package com.me.teedee;

public class BasicTower extends Tower {

	public BasicTower(Position pos){
		price[0] = new Price(1);
		currentLevel = 1;
		maxLevel = 1;
		attackSpeed[0] = 1;
		attackDamage[0] = 1;
		status = new Status();
		setPosition(pos);
	}
}
