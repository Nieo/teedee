package com.me.teedee.towers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.me.teedee.Position;

public class BasicTowerTest {
	AbstractTower t;
	@Before
	public void setUp(){
		t = new BasicTower(new Position(), null);
	}

	@Test
	public void testGetUpgradePrice() {

		this.setUp();
		assertEquals(100, t.getUpgradePrice().getPrice());
		t.upgrade();
		assertEquals(200, t.getUpgradePrice().getPrice());
		t.upgrade();
		assertEquals(300, t.getUpgradePrice().getPrice());
		t.upgrade();
		assertEquals(400, t.getUpgradePrice().getPrice());
		
	}

	@Test
	public void testHasTarget() {
		this.setUp();
		assertFalse(t.hasTarget());
	}

	@Test
	public void testUpgrade() {
		this.setUp();
		int lvl = t.getCurrentLevel();
		t.upgrade();
		assertEquals(lvl+1, t.getCurrentLevel());
	}

	@Test
	public void testDistance() {
		assertEquals(5f, AbstractTower.distance(new Position(), new Position(3f, 4f)), 0);
	}

}
