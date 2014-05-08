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
	
	private List<AbstractTower> towers = new ArrayList<AbstractTower>();
	private ArrayList<Wave> waves = new ArrayList<Wave>();
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

		//Creating the path
		path = new Path(pathPositions);

		//Creating a player
		player = new Player();

		//Creating the map
		map = new Map(WaveCreator.creatHardWave(path), path, player);
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

//	@Test
//	public void testSellTower() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testUpgradeTower() {
//		assertTrue(false);
//	}
//
//	@Test
//	public void testNextWave() {
//		assertTrue(false);
//	}
//
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
