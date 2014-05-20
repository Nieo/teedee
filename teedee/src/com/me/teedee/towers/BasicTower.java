package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class BasicTower extends AbstractTower {

	public BasicTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		price[0] = new Price(400);
		price[1] = new Price(200);
		price[2] = new Price(200);
		price[3] = new Price(300);
		price[4] = new Price(300);
		sellValue = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 0.3f;
		cooldown = attackSpeed[0];

		for(int i = 0; i < 5; i++)
			attackDamage[i] = 45 + 30 * i;

		status = new Status(0.5f,1,0);
		setPosition(pos);
		this.enemies = enemies;
		range = 200;
		id = 1;
	}
}
