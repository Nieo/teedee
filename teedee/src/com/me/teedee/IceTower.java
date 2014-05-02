package com.me.teedee;

import java.util.ArrayList;

public class IceTower extends AbstractTower {
	
	public IceTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(400);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(100*i);
		
		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1 + i/2;
		
		for(int i = 0; i < 5; i++)
			attackDamage[i] = 1 + i/2;
		
		status = new Status(0.5, 0, 500);
		setPosition(pos);
		this.enemies = enemies;
		range = 150;
		id = 2;
	}
}
