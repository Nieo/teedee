package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class BasicTower extends AbstractTower {

	public BasicTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(500);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(i * 100);

			value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 5 + i;
		
		for(int i = 0; i < 5; i++)
			attackDamage[i] = 1 + i;

		status = new Status(0.5,1,0);
		setPosition(pos);
		this.enemies = enemies;
		range = 200;
		id = 1;
	}
}
