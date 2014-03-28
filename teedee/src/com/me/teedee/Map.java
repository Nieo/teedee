package com.me.teedee;

import java.util.Iterator;
import java.util.List;

/**
@author Jacob Genander
*/
public class Map {
	private List<Tower> towers;
	private List<Wave> waves;
	private Path path;
	private Player player;
	private Wave currentWave;
	
	/**
	 * Constructs a map containing the given waves, path and player
	 * @param waves a list containing wave objects which are going to be used during the game
	 * @param path the path which enemies will travel on
	 * @param player the object representing the player of the game
	 */
	public Map(List<Wave> waves, Path path, Player player){
		this.waves = waves;
		this.path = path;
		this.player = player;
		if(!this.waves.isEmpty()){
			this.currentWave = this.waves.get(0);
		}
	}
	
	/**
	 * Returns a list containing all the enemies in the current wave
	 * @return a list containing all the enemies in the current wave
	 */
	public List<IEnemy> getEnemies(){
		return currentWave.getEnemies();
	}
	
	/**
	 * Returns a list of towers that are currently on the map
	 * @return a list of towers that are currently on the map
	 */
	public List<Tower> getTowers(){
		return towers;
	}
	
	/**
	 * Builds a tower on a position
	 * @param tower the tower to be built on the map
	 * @param position the position on the map which the tower will be built on
	 */
	public void buildTower(Tower tower, Position position){
		tower.setPosition(position);
		this.towers.add(tower);
	}
	
	/**
	 * Returns the map's path
	 * @return the path of the map
	 */
	public Path getPath(){
		return path;
	}
	
	/**
	 * Returnss the game's player
	 * @return the player currently playing the game
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Replaces the game's player
	 * @param player the player who will be playing the game
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * Setting the current wave of enemies to the next wave in the list of waves
	 */
	public void nextWave(){
		Iterator<Wave> waveIterator = waves.iterator();
		if(waveIterator.hasNext()){
			currentWave = waveIterator.next();
		}
	}

}
