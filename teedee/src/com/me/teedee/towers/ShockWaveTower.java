package com.me.teedee.towers;

import java.util.ArrayList;
import java.util.List;

import com.me.teedee.Position;
import com.me.teedee.Price;
import com.me.teedee.Status;
import com.me.teedee.enemies.AbstractEnemy;
/**
 * A class representing a tower with high range but low attack speed.
 * When the target is hit, its neighbors take damage. The nearest neighbor
 * takes more damage than ones that are farther away.
 */
public class ShockWaveTower extends AbstractTower{
	private int shockWaveRange = 300;
	public ShockWaveTower(Position pos, ArrayList<AbstractEnemy> enemies){
		price[0] = new Price(350);
		for(int i = 1; i < 5; i++){
			price[i] = new Price(200*i);
		}
			value = price[0].getPrice();
			currentLevel = 0;
			maxLevel = 5;
			for(int i = 0; i < 5; i++)
				attackSpeed[i] = 1;
			cooldown = attackSpeed[0];
			
			for(int i = 0; i < 5; i++)
				attackDamage[i] = 70 + i * 70;
			
			status = new Status(1f, 0, 1);
			setPosition(pos);
			this.enemies = enemies;
			range = 350;
			id = 4;
	}
	
	@Override
	public void shoot(float delta){
		if(cooldown - delta <= 0){
			for(AbstractEnemy enemy : getNeighbourEnemies()){
				int damage = (int)(attackDamage[currentLevel]*(1-distance(this.getTargetPosition().get(0), enemy.getPosition())/shockWaveRange));
				enemy.takeDamage(damage);
			}
		}
		super.shoot(delta);
	}
	
	public List<AbstractEnemy> getNeighbourEnemies(){
		List<AbstractEnemy> neighbours = new ArrayList<AbstractEnemy>();
		if(!this.getTargetPosition().isEmpty()){
			for(AbstractEnemy enemy : enemies){
				//If the distance between the target and any of the other enemies is less than 100 points
				if(!this.getTargetPosition().get(0).equals(enemy.getPosition())){
					if(distance(this.getTargetPosition().get(0), enemy.getPosition()) < shockWaveRange){
						neighbours.add(enemy);
					}
				}
			}
		}
		return neighbours;
	}

}
