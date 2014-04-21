package com.me.teedee;

import java.util.ArrayList;

public class BasicTower extends Tower {

	public BasicTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(500);
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 5 + i;
		
		for(int i = 0; i < 5; i++) {
			attackDamage[i] = 1 + i;
		}
		status = new Status();
		setPosition(pos);
		this.enemies = enemies;
		range = 200;
	}
}
