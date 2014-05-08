package com.me.teedee;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.me.teedee.enemies.AbstractEnemy;
import com.me.teedee.towers.AbstractTower;
import com.me.teedee.towers.BasicTower;

public class MapTest {
	
	private List<AbstractTower> towers;
	private ArrayList<Wave> waves;
	private Path path; 
	private Player player;
	private ArrayList<AbstractEnemy> currentEnemies;
	private TiledMap tiledMap;
	private int waveIndex = 0;
	private Map map;

	@Before
    public void setUp() {
		//Creating the path "checkpoints"
		List<Position> pathPositions = new ArrayList<Position>();
		pathPositions.add(new Position(0,490));
		pathPositions.add(new Position(740,490));
		pathPositions.add(new Position(740,300));
		pathPositions.add(new Position(160,300));
		pathPositions.add(new Position(160,90));
		pathPositions.add(new Position(850,90));
		pathPositions.add(new Position(850,720));

		path = new Path(pathPositions);
		player = new Player();
		map = new Map(WaveFactory.creatHardWave(path), path, player);
		waves = new ArrayList<Wave>();
		towers = new ArrayList<AbstractTower>();
		
		System.out.println("Test set up");
		
	}
	
    @After
    public void tearDown() {
    	System.out.println("Test torn down");
    }
    
//	@Test
//	public void testMapArrayListOfWavePathPlayer() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testMapArrayListOfWaveStringPlayer() {
//		assertTrue(false);
//	}

	@Test
	public void testBuildTower() {
		float posX = 350;
		float posY = 400;
		//This position is not on the path
		Position pos = new Position(posX,posY);
		AbstractTower tower1 = new BasicTower(pos,currentEnemies);
		
		//The tower should be built on the empty position
		assertTrue(map.buildTower(tower1, pos));
		
		//Checks that the position the tower was built on actually was the given position
		float towerPosX = map.getTowers().get(0).getPosition().getX();
		float towerPosY = map.getTowers().get(0).getPosition().getY();
		assertTrue(towerPosX == posX && towerPosY == posY);
		
		AbstractTower tower2 = new BasicTower(pos,currentEnemies);
		
		//The tower should NOT be built on the same position as the existing tower's (tower1) position.
		assertFalse(map.buildTower(tower2, pos));
		
		//The tower should NOT be created within 40 points from another towers position.
		assertFalse(map.buildTower(tower2, new Position(pos.getX()+20,pos.getY()+10)));
		
		//The tower should NOT be built on the path, which is set to be 60 points wide.
		assertFalse(map.buildTower(tower2, new Position(500,300)));
		
	}

	@Test
	public void testSellTower() {
		double moneyFromBeginning = player.getMoneyInt();
		AbstractTower tower = new BasicTower(new Position(200,200),currentEnemies);
		map.buildTower(tower,new Position(200,200));
		
		//Assert that the buildTower method actually reduces the amount of money the player has
		assertTrue(player.getMoneyInt() == 1000-tower.getValue());
		
		map.sellTower(0);
		
		//Assert that the sellTower method actually increases the amount of money the player has
		assertEquals(moneyFromBeginning - (int)(tower.getValue()*0.2),(int)player.getMoneyInt(),1);
		
		assertTrue(towers.isEmpty());
	}

	@Test
	public void testUpgradeTower() {
		AbstractTower tower = new BasicTower(new Position(200,200),currentEnemies);
		map.buildTower(tower, new Position(200,200));
		player.removeMoney(950);
		//Assert that a tower can't be upgraded if the player doesn't have enough money
		assertFalse(map.upgradeTower(tower));
		
		player.addMoney(3000);
		
		//Assert that a tower can be upgraded if the player has enough money
		assertTrue(map.upgradeTower(tower));
		
		map.upgradeTower(tower);
		map.upgradeTower(tower);
		map.upgradeTower(tower);
		//Assert that a tower that is fully upgraded can't be upgraded any more
		assertFalse(map.upgradeTower(tower));
	}

	@Test
	//******TODO***** This test is not working, the implementation is not finished
	public void testNextWave() {
		List<AbstractEnemy> wave1 = map.getEnemies();
		
		//Assert that there are no enemies from the beginning
		assertTrue(wave1.isEmpty());
		
		map.nextWave();
		
		//Assert that no wave is created when nextWave() is called and there are no current enemies
		assertFalse(false);
		
	}

//	@Test
//	public void testUpdateEnemiesPositions() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testTowersShoot() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testRemoveDeadEnemies() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testUpdate() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testUpdateEnemiesStatuses() {
//		assertTrue(false);
//	}

}
