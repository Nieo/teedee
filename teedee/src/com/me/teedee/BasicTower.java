package com.me.teedee;

import java.util.ArrayList;

public class BasicTower extends Tower {

	public BasicTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(1);
		currentLevel = 1;
		maxLevel = 1;
		attackSpeed[0] = 1;
		attackDamage[0] = 1;
		status = new Status();
		setPosition(pos);
		this.enemies = enemies;
	}
}
