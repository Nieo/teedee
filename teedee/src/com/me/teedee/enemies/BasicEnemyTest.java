package com.me.teedee.enemies;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.me.teedee.Path;
import com.me.teedee.Position;
import com.me.teedee.Status;
import com.me.teedee.screens.TowerView;
import com.me.teedee.towers.AbstractTower;
import com.me.teedee.towers.BasicTower;

public class BasicEnemyTest {
	
	protected AbstractEnemy a;
	protected void setUp(){
		List<Position> pathPos = new ArrayList<Position>();
		pathPos.add(new Position(10, 0));
		pathPos.add(new Position(10,200));
		a = new BasicEnemy(new Path(pathPos));
	}
	
	@Test
	public void testTakeDamage() {
		this.setUp();
		float maxLives = a.getLives().getMaxLives();
		a.takeDamage(10, new Status(1,0,0));
		assertEquals(maxLives-10, a.getLives().getCurrentLives(), 0.1f);
	}
	
	@Test
	public void testGetOverallStatus() {
		this.setUp();
		AbstractTower t1 = new BasicTower(new Position(), null);
		AbstractTower t2 = new BasicTower(new Position(), null);
		a.addTowerStatus(t1, new Status(0.8f,1f,1f));
		a.addTowerStatus(t2, new Status(0.8f,1f,1f));
		Status s = a.getOverallStatus();
		assertEquals(0.64f,s.getSpeedRatio(), 0.01f);
		assertEquals(2f, s.getDamagePerSecond(), 0.01f);
	}

	@Test
	public void testMove() {
		this.setUp();
		a.move(1f);
		assertEquals(10f, a.getPosition().getX(), 0);
		a.move(1f);
		assertEquals(120f, a.getPosition().getY(), 0);
	}

}
