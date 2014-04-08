package com.me.teedee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
@author Jacob Genander
*/
public class Map {
	
	private List<Tower> towers = new ArrayList<Tower>();
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private Path path; 
	private Player player;
	private ArrayList<AbstractEnemy> currentEnemies;
	private TiledMap tiledMap;
	
	/**
	 * Constructs a map containing the given waves, path and player
	 * @param waves a list containing wave objects which are going to be used during the game
	 * @param path the path which enemies will travel on
	 * @param player the object representing the player of the game
	 */
	public Map(ArrayList<Wave> waves, Path path, Player player) {
		this.waves = waves;
		this.path = path;
		this.player = player;
		if(!this.waves.isEmpty()) {
			this.currentEnemies = this.waves.get(0).getEnemies();
		}
		
		for(int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).setPosition(new Position(currentEnemies.get(i).getPosition().getX()-50*i,currentEnemies.get(i).getPosition().getY()));
		}
	}
	
	/**
	 * Constructs a map containing the given waves, a given TiledMap and player
	 * @param waves a list containing wave objects which are going to be used during the game
	 * @param mapPath the system path to the tmx map
	 * @param player the object representing the player of the game
	 */
	public Map(ArrayList<Wave> waves, String mapPath,Player player) {
		this.waves = waves;
		this.tiledMap = new TmxMapLoader().load(mapPath);
		this.player = player;
		//if(!this.waves.isEmpty()) {
			this.currentEnemies = this.waves.get(0).getEnemies();
		//}
	}
	
	/**
	 * Returns a list containing all the enemies in the current wave
	 * @return a list containing all the enemies in the current wave
	 */
	public List<AbstractEnemy> getEnemies() {
		return currentEnemies;
	}
	
	/**
	 * Returns a list of towers that are currently on the map
	 * @return a list of towers that are currently on the map
	 */
	public List<Tower> getTowers() {
		return towers;
	}
	
	/**
	 * Builds a tower on a position
	 * @param tower the tower to be built on the map
	 * @param position the position on the map which the tower will be built on
	 */
	public void buildTower(Tower tower, Position position) {
		tower.setPosition(position);
		this.towers.add(tower);
	}
	
	/**
	 * Returns the map's path
	 * @return the path of the map
	 */
	public Path getPath() {
		return path;
	}
	
	public TiledMap getTiledMap() {
		return this.tiledMap;
	}
	
	/**
	 * Returns the game's player
	 * @return the player currently playing the game
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Replaces the game's player
	 * @param player the player who will be playing the game
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Setting the current enemies to the next wave's enemies
	 */
	public void nextWave() {
		Iterator<Wave> waveIterator = waves.iterator();
		if(waveIterator.hasNext()) {
			//currentEnemies = waveIterator.next().getEnemies(); TODO Wave's method getEnemies() must return ArrayList, not just a List
		}
	}
	
	/**
	 * 
	 */
	public void updateEnemiesPositions() {
		if(!currentEnemies.isEmpty()) {
			for(int i = 0;i < currentEnemies.size(); i++) {
				if(currentEnemies.get(i).move())
						currentEnemies.remove(i);
			}
		}
	}
	
	/**
	 * 
	 */
	public void towersShoot() {
		//if(!towers.isEmpty()){
			for(Tower tower : towers) {
				//tower.shoot(); TODO uncomment this when the shoot method is implemented in class Tower
			}
		//}
	}
	public void removeDeadEnemies(){
		if(!currentEnemies.isEmpty()){
			for(int i = 0; i < currentEnemies.size();i++){
				if(currentEnemies.get(i).isAlive())
					currentEnemies.remove(i);
			}
		}
	}
	
	public void update() {
		this.updateEnemiesPositions();
		this.towersShoot();
		this.removeDeadEnemies();
	}
	
	public void startGame() {
		while(true) {
			try {
				update();
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
