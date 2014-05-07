package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class IceTower extends AbstractTower {
	
	public IceTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(400);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(100*i);
		
		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1;
		
		for(int i = 0; i < 5; i++)
			attackDamage[i] = 1 + i/2;
		
		status = new Status(0.5, 0, 1);
		setPosition(pos);
		this.enemies = enemies;
		range = 150;
		id = 2;
	}
}
