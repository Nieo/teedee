package com.me.teedee.towers;

import java.util.ArrayList;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;

public class IceTower extends AbstractTower {
		ArrayList<Status> statusLevels = new ArrayList<Status>();
	public IceTower(Position pos, ArrayList<AbstractEnemy> enemies) {
		
		statusLevels.add(new Status(0.75f,0,1.7f));
		statusLevels.add(new Status(0.70f,0,1.8f));
		statusLevels.add(new Status(0.65f,0,1.9f));
		statusLevels.add(new Status(0.60f,0,2f));
		statusLevels.add(new Status(0.50f,0,2.2f));
		
		price[0] = new Price(400);
		for(int i = 1; i < 5; i++)
			price[i] = new Price(100*i);
		
		value = price[0].getPrice();
		currentLevel = 0;
		maxLevel = 5;
		for(int i = 0; i < 5; i++)
			attackSpeed[i] = 1;
		cooldown = attackSpeed[0];
		
		for(int i = 0; i < 5; i++)
			attackDamage[i] = 20 + 10*i;
		
		status = statusLevels.get(0);
		setPosition(pos);
		this.enemies = enemies;
		range = 150;
		id = 2;
		
		
		
	}
	
	
	@Override
	public Boolean upgrade(){
		if(super.upgrade()){
			status = statusLevels.get(currentLevel);
			return true;
		}
		return false;
	}
}
