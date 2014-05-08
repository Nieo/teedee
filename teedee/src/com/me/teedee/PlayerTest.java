package com.me.teedee;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testAddMoney() {
		Player player = new Player();
		player.addMoney(300);
		player.addMoney(20);
		//The player has 1000 in money when instantiated
		assertTrue(player.getMoneyInt() == 1320);
	}

	@Test
	public void testRemoveMoney() {
		Player player = new Player();
		player.addMoney(512);
		player.removeMoney(12);
		player.removeMoney(100);
		//The player has 1000 in money when instantiated
		assertTrue(player.getMoneyInt() == 1400);
		
	}

	@Test
	public void testTakeDamage() {
		Player player = new Player();
		player.takeDamage(75);
		player.takeDamage(5);
		//The player has 100 in lives when instantiated
		assertTrue(player.getLives().getLivesHealth() == 20);
		
	}

}
